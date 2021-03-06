package com.toni.ferreiro.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.toni.ferreiro.app.view.xml.ClienteList;
import com.toni.ferreiro.models.entity.Cliente;
import com.toni.ferreiro.models.serviceInterface.ClienteServiceInterface;
import com.toni.ferreiro.models.serviceInterface.UploadFileServiceInterface;
import com.toni.ferreiro.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ClienteServiceInterface clienteService;

	@Autowired
	private UploadFileServiceInterface uploadFileService;

	@Autowired
	private MessageSource messageSource;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" }) // se podria dejar un solo rol entre llaves
	@GetMapping(value = "/upload/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

//	@Secured("ROLE_USER")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')") // se podria usar hasAnyRole para varios roles
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.fetchByIdWithFacturas(id);// clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("danger", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		return "ver";
	}

	@GetMapping(value = "/listar-rest")
	public @ResponseBody ClienteList listarRest() { // en el caso que queramos pasar el json a xml, necesitamos el
													// wraper
//	public @ResponseBody List<Cliente> listarRest() {
		// return clienteService.findAll();
		return new ClienteList(clienteService.findAll());
	}

	@RequestMapping(value = { "/listar", "/" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request, Locale locale) {

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es :".concat(authentication.getName()));
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			logger.info(
					"Utilizando forma estática SecurityContextHolder.getContext().getAuthentication(): usuario autenticado, username :"
							.concat(auth.getName()));
		}

		if (hasRole("ROLE_ADMIN")) {
			logger.info("Hola ".concat(auth.getName().concat(" tienes acceso")));
		} else {
			logger.info("Hola ".concat(auth.getName().concat(" no tienes acceso")));
		}

		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"ROLE_");

		if (securityContext.isUserInRole("ADMIN")) { // se añade ROLE_ si dejas "" en la instancia de la clase
			logger.info("Utilizando forma SecurityContextHolderAwareRequestWrapper "
					.concat(auth.getName().concat(" tienes acceso")));
		} else {
			logger.info("Utilizando forma SecurityContextHolderAwareRequestWrapper "
					.concat(auth.getName().concat(" no tienes acceso")));
		}

		if (request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Utilizando forma HttpServletRequest ".concat(auth.getName().concat(" tienes acceso")));
		} else {
			logger.info("Utilizando forma HttpServletRequest ".concat(auth.getName().concat(" no tienes acceso")));
		}

//		Pageable pageRequest = new PageRequest(page, 5); PageRequest is deprecated con spring boot 2
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

//		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("titulo", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}

//	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("danger", "No existe el cliente");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("danger", "No existe el cliente");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, RedirectAttributes flash, Model model,
			@RequestParam("file") MultipartFile foto, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Ha subido correctamente " + uniqueFilename);

			cliente.setFoto(uniqueFilename);

		}

		String flashMessage = (cliente.getId() != null) ? "Cliente editado correctamente"
				: "Cliente creado correctamente";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", flashMessage);
		return "redirect:listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente borrado correctamente");

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada correctamente");
			}

		}

		return "redirect:/listar";
	}

	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}
		Authentication auth = context.getAuthentication();
		if (auth == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		/*
		 * for (GrantedAuthority authority : authorities) { if
		 * (role.equals(authority.getAuthority())) {
		 * logger.info("Hola ".concat(auth.getName().concat(" ,tu rol es ").concat(
		 * authority.getAuthority()))); return true; } } return false;
		 */
		return authorities.contains(new SimpleGrantedAuthority(role));
	}

}
