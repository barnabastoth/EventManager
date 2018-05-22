package application.controller;

import application.model.authentication.EditUser;
import application.model.authentication.NewEditSure;
import application.model.authentication.Role;
import application.model.authentication.User;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.service.UserService;
import application.utils.AuthenticationUtils;
import application.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private UserUtils userUtils;
    @Autowired private RoleRepository roleRepository;
    @Autowired private AuthenticationUtils authenticationUtils;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> listUser(){ return userService.findAll(); }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "username") String username){ return userService.findByUsername(username); }

    @GetMapping("/{username}/edit")
    public EditUser serveEditUser(@PathVariable("username") String username) {
        return userRepository.getEditUserByUsername(username);
    }

    @PostMapping("/{username}/edit")
    public ResponseEntity<?> saveUserEdit(@RequestBody NewEditSure newEditSure) {
        userUtils.saveUserEdit(newEditSure);
        return null;
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/{username}/addAdmin")
    public ResponseEntity<?> addAdmin(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            switch (user.get().getRole().getRole()) {
                case "Felhasználó":
                    Role newRole = roleRepository.findByRole("Admin");
                    user.get().setRole(newRole);
                    userRepository.saveAndFlush(user.get());
                    response.put("Message", username + " nevü felhasználó mostantól Admin jogosultságokkal rendelkezik az oldalon.");
                    response.put("Role", newRole.getRole());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Admin":
                    response.put("Message", username + " nevü felhasználó már Admin jogosultságokkal rendelkezik az oldalon.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Tulajdonos":
                    response.put("Message", username + " nevü felhasználó Tulajdonos az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/{username}/removeAdmin")
    public ResponseEntity<?> removeAdmin(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            switch (user.get().getRole().getRole()) {
                case "Felhasználó":
                    response.put("Message", username + " nevü felhasználó már igyis csak Felhasználói jogosultságokkal rendelkezik.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Admin":
                    Role newRole = roleRepository.findByRole("Felhasználó");
                    response.put("Role", newRole.getRole());
                    response.put("Message", username + " nevü felhasználó mostantól csak Felhasználói jogosultságokkal rendelkezik..");
                    user.get().setRole(newRole);
                    userRepository.saveAndFlush(user.get());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Tulajdonos":
                    response.put("Message", username + " nevü felhasználó Tulajdonos az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/{username}/ban")
    public ResponseEntity<?> banUser(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            switch (user.get().getRole().getRole()) {
                case "Felhasználó":
                    if(user.get().getActive() == 0) {
                        response.put("Message", username + " nevü felhasználó már ki van tiltva az oldalról.");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    user.get().setActive(0);
                    userRepository.saveAndFlush(user.get());
                    response.put("Message", username + " nevü felhasználó mostantól ki van tiltva, nem fog tudni többet bejelentkezni.");
                    response.put("Active", user.get().getActive());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Admin":
                    response.put("Message", username + " nevü felhasználó Admin az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Tulajdonos":
                    response.put("Message", username + " nevü felhasználó Tulajdonos az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Tulajdonos')")
    @GetMapping("/{username}/unban")
    public ResponseEntity<?> unBanUser(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            switch (user.get().getRole().getRole()) {
                case "Felhasználó":
                    if (user.get().getActive() == 1) {
                        response.put("Message", username + " nevü felhasználó nincs kitiltva az oldalról.");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    user.get().setActive(1);
                    userRepository.saveAndFlush(user.get());
                    response.put("Message", username + " nevü felhasználó mostantól nincs kitiltva az oldalról.");
                    response.put("Active", user.get().getActive());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Admin":
                    response.put("Message", username + " nevü felhasználó Admin az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case "Tulajdonos":
                    response.put("Message", username + " nevü felhasználó Tulajdonos az oldalon. Ehez a müvelethez nincs jogod.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
