package application.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    //StandardServletMultipartResolver
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleUploadSizeException(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        return "redirect:"+ request.getHeader("Referer");

    }
}
