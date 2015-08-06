
package rajan.springmvc.spitter.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import rajan.springmvc.spitter.persistence.Spitter;
import rajan.springmvc.spitter.service.SpitterService;

@Controller
@RequestMapping("/spitters")
public class SpitterController {

	 @Resource(name = "spitterService")
	 private SpitterService spitterService;

	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createSpitterProfile(Model model) {
		System.out.println("I am at SC");
		model.addAttribute("spitter", new Spitter());
		return "spitters/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addSpitterFromForm(@Valid Spitter spitter,
			BindingResult bindingResult){
//			@RequestParam(value = "image", required = false) MultipartFile image) {
		System.out.println("Argument Received in SC are: "+spitter.getFullName()+spitter.getEmail()+spitter.getEmail());
		System.out.println("I am about to validate");
		if (bindingResult.hasErrors()) {
			return "spitters/edit";
		}
		System.out.println("Validated");
		
		spitterService.saveSpitter(spitter);
		
//		try {
//			if (!image.isEmpty()) {
//				validateImage(image);
//
////				saveImage(spitter.getId() + ".jpg", image);
//			}
//		} catch (FileUploadException e) {
//			bindingResult.reject(e.getMessage());
//			return "spitters/edit";
//		}
		return "redirect:/spitters/" + spitter.getUsername();
	}

//	private void validateImage(MultipartFile image) throws FileUploadException {
//		if (!image.getContentType().equals("image/jpeg")) {
//			throw new FileUploadException("Ony JPG images accepted");
//		}
//		
//	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username,
		Model model){
		model.addAttribute("spitter", spitterService.getSpitter(username));
		return "spitters/view";
	}
	
}