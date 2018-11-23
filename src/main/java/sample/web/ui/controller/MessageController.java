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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.crosscutting.MyExecutionTime;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping("/")
public class MessageController {

	private static final String FORM_TEMPLATE = "messages/form";
	private static final String LIST_TEMPLATE = "messages/list";
	private final MessageRepository messageRepository;
	private final BaseOrderRepository baseOrderRepository;
	private final OrderOptionRepository orderOptionRepository;
	private final ProductCatalogRepository productCatalogRepository;
	private final ProductRepository productRepository;

	@Autowired
	public MessageController(final MessageRepository messageRepository, final BaseOrderRepository baseOrderRepository,
							 final ProductCatalogRepository productCatalogRepository, final OrderOptionRepository orderOptionRepository,
                             final ProductRepository productRepository) {
		this.messageRepository = messageRepository;
		this.baseOrderRepository = baseOrderRepository;
		this.orderOptionRepository = orderOptionRepository;
		this.productCatalogRepository = productCatalogRepository;
		this.productRepository = productRepository;
	}

	private ProductCatalog createProductCatalogAndProductsAndOrder() {
		ProductCatalog productCatalog = new ProductCatalog();
		productCatalog = productCatalogRepository.save(productCatalog);
		log.debug("{} products in product catalog.", productCatalog.getProducts().size());

		Product schroefje = new Product("schroefje", 2);
		productRepository.save(schroefje);

		Product moertje = new Product("moertje", 1);
		productRepository.save(moertje);

		productCatalog.add(schroefje, 5);
		productCatalog.add(moertje, 5);

		try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
		    log.debug("InterruptedException: {}", e.getMessage(), e);
        }

		return productCatalogRepository.save(productCatalog);
	}

/*
	@MyExecutionTime
	private void decorateOrder() {
        Optional<Order> concreteOrder  = baseOrderRepository.findById(4L);
        if (concreteOrder.isPresent()) {
            OrderOption decoratedOrder1 = new OrderOption("wrapping paper", 7, concreteOrder.get());
            orderOptionRepository.save(decoratedOrder1);
            OrderOption decoratedOrder2 = new OrderOption("nice box", 5, decoratedOrder1);
            orderOptionRepository.save(decoratedOrder2);
            OrderOption decoratedOrder3 = new OrderOption("fast delivery", 12, decoratedOrder2);
            orderOptionRepository.save(decoratedOrder3);
            log.info("Content of the order: {}", decoratedOrder3);
            log.info("Price of the order: {}", decoratedOrder3.price());
        } else {
            throw new RuntimeException("Order with id 4 not found.");
        }
    }
*/
	private void createOrder() {
        ProductCatalog productCatalog = productCatalogRepository.findById(1L).orElseThrow(() -> new RuntimeException("ProductCatalog with id 1 not found"));

        Product product = productCatalog.decrementStock(2L);

        Product copy = new Product(product);

        Order order = new Order();
        order = baseOrderRepository.save(order);
        order.add(copy);

//        System.exit(0);
    }

    @MyExecutionTime
	@Transactional
	@GetMapping(path = "/create-order")
	public ResponseEntity createAndDecorateOrder() {
        createOrder();
//        decorateOrder();

		return new ResponseEntity<>(CREATED);
	}

    @MyExecutionTime
	@GetMapping(path = "/orders")
    public ResponseEntity<List<BaseOrder>> getOrders() {
	    return new ResponseEntity<>(baseOrderRepository.findAll(), OK);
    }

    @MyExecutionTime
    @GetMapping("/create-catalog")
    public ResponseEntity<ProductCatalog> createProductCatalog() {
        return new ResponseEntity<>(createProductCatalogAndProductsAndOrder(), OK);
    }

    @MyExecutionTime
	@GetMapping
	@Transactional
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
