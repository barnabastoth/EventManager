package application.controller;

import application.model.authentication.EditUser;
import application.model.authentication.EditUserPojo;
import application.model.authentication.User;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.DataExtractionUtils;
import application.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private UserUtils userUtils;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> listUser(){ return userService.findAll(); }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "username") String username){ return userService.findByUsername(username); }

    @GetMapping("/{username}/edit")
    public EditUser serveEditUser(@PathVariable("username") String username) {
        return userRepository.getEditUserByUsername(username);
    }

    @PostMapping("/{username}/edit")
    public ResponseEntity<?> saveUserEdit(@RequestBody EditUserPojo editUserPojo) {
        userUtils.saveUserEdit(editUserPojo);
        return null;
    }

    @GetMapping(value = "/profilepic/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveProfileImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        User user = userService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if(user != null) {
            if(user.getImage() != null) {
                response.getOutputStream().write(user.getImage());
            } else {
                ClassLoader classLoader = getClass().getClassLoader();
                File defaultProfilePic = new File(classLoader.getResource("images/user.png").getFile());
                BufferedImage originalImage = ImageIO.read(defaultProfilePic);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "png", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();
                response.getOutputStream().write(imageInByte);
            }
        }
    }
}
