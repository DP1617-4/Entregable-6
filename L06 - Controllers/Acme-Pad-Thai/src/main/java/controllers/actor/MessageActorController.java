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

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.FolderService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;


@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController{
	
	//Services
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FolderService folderService;

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
		Folder folder;
		folder = folderService.findOneToEdit(folderId);
		
		try{
			messages = messageService.findAllByFolder(folderId);
			result = new ModelAndView("message/list");
			result.addObject("messages", messages);
			result.addObject("requestURI", requestURI);
			result.addObject("folder", folder);
		}catch(Throwable oops){
			
			result = new ModelAndView("redirect:/folder/actor/list.do");
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
		Message sent;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				sent = messageService.send(message);
				result = new ModelAndView("redirect:/message/actor/list.do?folderId="+sent.getFolder().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(message, "message.commit.error");				
			}
		}

		return result;
	}
	
	//TODO Cuando lanza la excepción a dónde lo mando?
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int messageId){
		ModelAndView result;
		
		Message message;
		
		try{
			message = messageService.findOne(messageId);
			messageService.delete(message);
			result = new ModelAndView("redirect:/message/actor/list.do?folderId="+message.getFolder().getId());
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
			result.addObject("errorMessage", "message.commit.error");
		}
		
		return result;
	}
	
	//TODO lo mismo que arriba
	@RequestMapping(value="/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam int messageId){
		ModelAndView result;
		
		Message message;
		
		message = messageService.findOne(messageId);
		if(message == null){
			result = new ModelAndView("redirect:/welcome/index.do");
			result.addObject("errorMessage" , "message.missing");
		}	else
			result = createMoveModelAndView(message, null);
		
		
		return result;
	}
	
	@RequestMapping(value="/move", method = RequestMethod.POST, params = "save")
	public ModelAndView move(@Valid Message message, BindingResult binding){
		ModelAndView result;
		
		
		if(binding.hasErrors())
			result = createMoveModelAndView(message, null);
		else{
			try{
				messageService.move(message, message.getFolder());
				result = new ModelAndView("redirect:/message/actor/list.do?folderId="+message.getFolder().getId());
			}catch(Throwable oops){
				result = createMoveModelAndView(message, "message.commit.error");
			}
		}
		
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
		
		protected ModelAndView createMoveModelAndView(Message msg, String message) {
			ModelAndView result;
			Collection<Folder> folders;
		
			folders = folderService.findAllByPrincipal();
			
			
			result = new ModelAndView("message/move");
			result.addObject("errorMessage", message);
			result.addObject("message", msg);
			result.addObject("folders", folders);

			return result;
		}

}
