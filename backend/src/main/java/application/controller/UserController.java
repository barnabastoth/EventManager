package application.controller;

import application.model.authentication.User;
import application.service.UserService;
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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){ return userService.findAll(); }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @GetMapping(value = "/user/profilepic/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveProfileImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        User user = userService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
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
