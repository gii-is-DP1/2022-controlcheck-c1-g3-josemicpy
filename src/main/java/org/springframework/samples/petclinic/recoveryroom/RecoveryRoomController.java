package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {

	private final RecoveryRoomService service;

	private static final String VIEWS_RECOVERYROOM_CREATE_FORM = "recoveryRoom/createOrUpdateProductForm";
	private static final String VIEWS_RECOVERYROOM_LIST = "recoveryRoom/recoveryRoomList";

	@ModelAttribute("recoveryRoomTypes")
	public List<RecoveryRoomType> populateRecoveryRoomTypes() {
		return this.service.getAllRecoveryRoomTypes();
	}

	@Autowired
	public RecoveryRoomController(RecoveryRoomService recoveryRoomService) {
		this.service = recoveryRoomService;
	}

	@GetMapping
	public ModelAndView showRecoveryRoomList() {
		ModelAndView mav = new ModelAndView(VIEWS_RECOVERYROOM_LIST);
		mav.addObject("recoveryrooms", service.getAll());
		return mav;
	}

	@GetMapping("/create")
	public ModelAndView createRecoveryRoom() {
		ModelAndView mav = new ModelAndView(VIEWS_RECOVERYROOM_CREATE_FORM);
		mav.addObject("recoveryroom", new RecoveryRoom());
		return mav;
	}

	@PostMapping("/create")
	public ModelAndView processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result)
			throws DuplicatedRoomNameException {
		ModelAndView mav;
		if (result.hasErrors()) {
			mav = new ModelAndView(VIEWS_RECOVERYROOM_CREATE_FORM);
			mav.addObject("recoveryroom", recoveryRoom);
			mav.addObject("types", service.getAllRecoveryRoomTypes());
		} else {
			this.service.save(recoveryRoom);
			mav = new ModelAndView("welcome");
		}
		return mav;
	}
}
