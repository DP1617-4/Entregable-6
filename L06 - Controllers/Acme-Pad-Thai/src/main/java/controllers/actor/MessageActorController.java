package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;


@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController{
	
	//Services
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ActorService actorService;
	
	
	//Contructor
	
	public MessageActorController(){
		super();
	}
	
	//Listing
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId){
		
		ModelAndView result;
		Collection<Message> messages;
		String requestURI = "message/actor/list.do?folderId="+folderId;
		
		try{
			messages = messageService.findAllByFolder(folderId);
			result = new ModelAndView("message/list");
			result.addObject("messages", messages);
			result.addObject("requestURI", requestURI);
		}catch(Throwable oops){
			
			result = new ModelAndView("redirect: /folder/actor/list.do");
			result.addObject("message", "message.folder.wrong");
			
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		Message message= messageService.create();
		
		result = createEditModelAndView(message);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params ="save")
	public ModelAndView save(@Valid Message message, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				messageService.send(message);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(message, "message.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int messageId){
		ModelAndView result;
		
		Message message;
		
		message = messageService.findOne(messageId);
				
		messageService.delete(message);
		
		
		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	
		protected ModelAndView createEditModelAndView(Message message) {
			ModelAndView result;

			result = createEditModelAndView(message, null);
			
			return result;
		}	
		
		protected ModelAndView createEditModelAndView(Message msg, String message) {
			ModelAndView result;
			Collection<Actor> actors;
		
			actors = actorService.findAll();
			
			
			result = new ModelAndView("message/edit");
			result.addObject("errorMessage", message);
			result.addObject("message", msg);
			result.addObject("actors", actors);

			return result;
		}
	

}
