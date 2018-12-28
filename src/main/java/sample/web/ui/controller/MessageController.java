/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.web.ui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.crosscutting.MyExecutionTime;
import sample.web.ui.domain.Message;
import sample.web.ui.repository.MessageRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
	private static final String FORM_TEMPLATE = "messages/form";
	private static final String LIST_TEMPLATE = "messages/list";

	private final MessageRepository messageRepository;

	@Autowired
	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@MyExecutionTime
	@Transactional
	@GetMapping
	public ModelAndView list() {
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView(LIST_TEMPLATE, "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView("messages/view", "message", message);
	}

	@Transactional
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Message message) {
		return FORM_TEMPLATE;
	}

	@PostMapping
	public ModelAndView create(@Valid Message message, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView(FORM_TEMPLATE, "formErrors", result.getAllErrors());
		}
		message = this.messageRepository.save(message);
		redirect.addFlashAttribute("globalMessage", "view.success");
		return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@GetMapping("delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteById(id);
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView(LIST_TEMPLATE, "messages", messages);
	}

	@GetMapping("modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView(FORM_TEMPLATE, "message", message);
	}

}
