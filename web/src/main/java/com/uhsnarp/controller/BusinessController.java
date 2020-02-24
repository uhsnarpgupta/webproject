package com.uhsnarp.controller;

import com.uhsnarp.model.BusinessBO;
import com.uhsnarp.model.ImageBO;
import com.uhsnarp.model.UserBO;
import com.uhsnarp.services.BusinessService;
import com.uhsnarp.services.CategoryService;
import com.uhsnarp.services.ProductService;
import com.uhsnarp.services.UserService;
import com.uhsnarp.interceptor.RequestInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping({"/business"})
@Controller
public class BusinessController extends RequestInterceptor {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Initializing Logger
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("ProductInterceptor: REQUEST Intercepted for URI: "
                + request.getRequestURI());
        String username = request.getAttribute("username").toString();
        request.setAttribute("username", "sample");
        return true;
    }

    // Home page - index of all GIFs
    @RequestMapping("")
    public String listBusiness(Model model, HttpSession httpSession, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        //getCookieDetails(model, request);
        // Get all productBOS
        Set<BusinessBO> businessBOS = businessService.findAll();

        boolean aclass = false;
        if (null != redirectAttributes.getFlashAttributes().get("aclass")) {
            logger.info("Map of redirectAttributes: " + redirectAttributes.getFlashAttributes());
            logger.info("redirectAttribute->aclass: " + redirectAttributes.getFlashAttributes().get("aclass"));
            aclass = Boolean.valueOf(redirectAttributes.getFlashAttributes().get("aclass").toString());
        }

        model.addAttribute("business", businessBOS);
        return "index";
    }

    private void getCookieDetails(Model model, HttpServletRequest request) {
        String mobileNum = getCookieValue(request, "mobileNumber");
        boolean guestMember = true;
        if (!StringUtils.isEmptyOrWhitespace(mobileNum) && null != userService.findById(Integer.parseInt(mobileNum))) {
            guestMember = false;
        }
        model.addAttribute("guestMember", guestMember);
        logger.info(" guestMember :" + guestMember);
    }

    // Single PRODUCT page
    @RequestMapping("/{businessId}")
    public String productDetails(@PathVariable Integer productId, Model model, HttpSession httpSession, HttpServletRequest request) {
        // Get product whose id is gifId

        getCookieDetails(model, request);
        BusinessBO businessBO = businessService.findById(productId);

        model.addAttribute("business", businessBO);

        return "businessBO/details";
    }

    // BUSINESS businessBO data
    @RequestMapping("/{businessId}.business")
    @ResponseBody //no thymeleaf comin in picture in case of this URI
    public byte[] businessImage(@PathVariable Integer businessId, HttpSession httpSession) {
        // Return image data as byte array of the BUSINESS whose id is gifId
        Set<ImageBO> images = productService.findById(businessId).getImages();
        return images.iterator().next().getPortraitImage();
    }


    // Add a new BUSINESS
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addBusiness(BusinessBO businessBO, RedirectAttributes redirectAttributes,
                              HttpServletResponse response, HttpServletRequest request) {
        // Add new BUSINESS if data is valid
        String mobileNum = getCookieValue(request, "mobileNumber");
        boolean guestMember = true;
        /*if (null != mobileNum) {
            UserBO member = userService.findByBusiness());
            if (!StringUtils.isEmptyOrWhitespace(mobileNum) && null != member) {
                guestMember = false;
            }
        }*/
        Optional<UserBO> usr = userService.findAll().stream().filter(user -> {
            logger.info("BusinessBO Mob No :" + businessBO.getMobileNumber());
            return businessBO.getId() == user.getId();
        }).findAny();
        logger.info(" User Present :" + usr.isPresent());

        if (usr.isPresent()) {
            userService.save(usr.get());
            //businessBO.setOwnerBO(usr.get());
            logger.info(" User details updated :" + usr.get());
        }
        logger.info(" BusinessBO details :" + businessBO);
        businessService.save(businessBO);
        Cookie cookie = new Cookie("username", businessBO.getName());
        cookie.setMaxAge(1000); //set expire time to 1000 sec
        response.addCookie(new Cookie("username", businessBO.getName())); //put cookie in response

        // Add flash message for success
        //redirectAttributes.addFlashAttribute("flash", new FlashMessage("BUSINESS successfully addded", FlashMessage.Status.SUCCESS));

        // Redirect browser to new BUSINESS's detail view
        return String.format("redirect:/businessBO/%s", businessBO.getId());
    }


    // Form for uploading a new PRODUCT
    @RequestMapping("/addBusiness")
    public String formNewProduct(Model model, HttpSession httpSession) {
        // Add model attributes needed for new PRODUCT upload form
        model.addAttribute("business", new BusinessBO());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("action", "/businessBO");
        model.addAttribute("heading", "add");
        model.addAttribute("submit", "Add");
        return "businessBO/form";
    }

    // Form for editing an existing BUSINESS
    @RequestMapping(value = "/{businessId}/edit")
    public String formEditProduct(@PathVariable Integer productId, Model model, HttpSession httpSession) {
        // Add model attributes needed for edit form
        if (!model.containsAttribute("businessBO")) {
            model.addAttribute("business", businessService.findById(productId));
        }
        model.addAttribute("action", String.format("/productBOS/%s", productId));
        model.addAttribute("heading", "Edit");
        model.addAttribute("submit", "Update");
        model.addAttribute("categories", categoryService.findAll());
        return "businessBO/form";
    }

    // Update an existing BUSINESS
    @RequestMapping(value = "/{businessId}", method = RequestMethod.POST)
    public String updateProduct(@Valid BusinessBO businessBO, @RequestParam MultipartFile file,
                                BindingResult result, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        // TODO: Update BUSINESS if data is valid
        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", result);

            // Add  categoryBO if invalid was received
            redirectAttributes.addFlashAttribute("business", businessBO);

            // Redirect back to the form (In location header -> /categories/add)
            return String.format("redirect:/businessBO/%s/edit", businessBO.getId());
        }

        businessService.save(businessBO);

        //redirectAttributes.addFlashAttribute("flash", new FlashMessage("BusinessBO successfully modified!", FlashMessage.Status.SUCCESS));

        // TODO: Redirect browser to updated BUSINESS's detail view
        return "redirect:/";
    }

    // Delete an existing PRODUCT
    @RequestMapping(value = "/{businessId}/delete", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable Integer productId, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        // TODO: Delete the BUSINESS whose id is gifId
        BusinessBO businessBO = businessService.findById(productId);

        businessService.delete(businessBO);
        //redirectAttributes.addFlashAttribute("flash", new FlashMessage("BusinessBO deleted!", FlashMessage.Status.SUCCESS));

        // Redirect to app root
        return "redirect:/";
    }

    // Search results
    @RequestMapping("/searchBusiness")
    public String searchResults(@RequestParam String q, Model model, HttpSession httpSession) {
        // Get list of PRODUCTs whose description contains value specified by q
        List<BusinessBO> businessBOS = new ArrayList<>();
        businessService.findAll().forEach(product -> {
            if ((product.getName().toLowerCase()).indexOf(q.toLowerCase()) != -1) {
                businessBOS.add(product);
            }
        });

        model.addAttribute("products", businessBOS);
        return "product/index";
    }
}
