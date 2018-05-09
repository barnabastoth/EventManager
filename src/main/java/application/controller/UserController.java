package application.controller;

import application.model.authentication.User;
import application.service.UserService;
import application.utils.DataExtractionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private DataExtractionUtils dataExtractionUtils;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){ return userService.findAll(); }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "username") String username){ return userService.findByUsername(username); }

    @GetMapping(value = "/user/profilepic/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveProfileImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        Optional<User> user = userService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if(user.isPresent()) {
            if(user.get().getImage() != null) {
                response.getOutputStream().write(user.get().getImage());
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
