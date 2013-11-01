//package org.openmrs.module.radiologyrest.web;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.List;
//import java.util.Vector;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.openmrs.Order;
//import org.openmrs.api.OrderService;
//import org.openmrs.api.PatientService;
//import org.openmrs.api.context.Context;
//import org.openmrs.module.radiologyrest.Main;
//import org.openmrs.module.radiologyrest.Roles;
//import org.openmrs.module.radiologyrest.Study;
//import org.openmrs.module.radiologyrest.Study.Modality;
//import org.openmrs.module.radiologyrest.Study.Priorities;
//import org.openmrs.module.radiologyrest.Utils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class PortletsController {
//
//	Log log = LogFactory.getLog(getClass());
//	String str = "";
//
//	static Main service() {
//		return Context.getService(Main.class);
//	}
//
//	@RequestMapping("/module/radiologyrest/portlets/patientOverview.portlet")
//	String getPatientInfoRoute() {
//		String route = "module/radiologyrest/portlets/patientOverview";
//		return route;
//	}
//	
//
//	@RequestMapping(value = "/module/radiologyrest/portlets/addOrderFields.portlet")
//	ModelAndView handleAddOrderFieldsRequest(
//			@RequestParam(value = "modality", required = true) String modality) {
//
//		ModelAndView mav = new ModelAndView(
//				"module/radiologyrest/portlets/addOrderFields");
//		mav.addObject("modality", modality);
//
//		return mav;
//
//	}
//
//	@RequestMapping(value = "/module/radiologyrest/portlets/orderSearch.portlet")
//	ModelAndView ordersTable(
//			@RequestParam(value = "patientQuery", required = false) String patientQuery,
//			@RequestParam(value = "startDate", required = false) String startDateS,
//			@RequestParam(value = "finalDate", required = false) String finalDateS,
//			@RequestParam(value = "pending", required = false) boolean pending,
//			@RequestParam(value = "completed", required = false) boolean completed) {
//		str = "";
//		String route = "module/radiologyrest/portlets/orderSearch";
//		ModelAndView mav = new ModelAndView(route);
//
//		List<Order> matchedOrders = dateFilter(patientQuery, startDateS,
//				finalDateS, mav);
//		// TODO Status filter
//		List<Study> studies=Study.get(matchedOrders);
//		List<String> statuses=new Vector<String>();
//		List<String> priorities=new Vector<String>();
//		List<String> schedulers=new Vector<String>();
//		List<String> performings=new Vector<String>();
//		List<String> readings=new Vector<String>();
//		List<String> modalities=new Vector<String>();
//                List<String> mwlStatuses=new Vector<String>();
//		for (Study study : studies) {
//			if (study!=null) {
//				statuses.add(study.getStatus(Context.getAuthenticatedUser()));
//				priorities.add(Priorities.string(study.getPriority(),true));
//				schedulers.add(study.scheduler());
//				performings.add(study.performing());
//				readings.add(study.reading());
//				modalities.add(Modality.values()[study.getModality()].toString());
//                                mwlStatuses.add(study.mwlStatus());
//			}
//		}
//		
//
//		// TODO optimize all the function, get orders and studies(priorities, statuses, etc) in a row
//		
//		
//		// Response variables
//
//		mav.addObject(matchedOrders);
//		mav.addObject("statuses", statuses);
//		mav.addObject("priorities", priorities);
//		mav.addObject("schedulers", schedulers);
//		mav.addObject("performings", performings);
//		mav.addObject("readings", readings);
//		mav.addObject("modalities", modalities);
//		mav.addObject("matchedOrdersSize", matchedOrders.size());
//		if(Context.getAuthenticatedUser().hasRole(Roles.ReadingPhysician, true)){
//			mav.addObject("obsId", "&obsId");
//		}
//                mav.addObject("mwlStatuses", mwlStatuses);
//
//		log.debug("\n***\n" + str + "\n///");
//		return mav;
//	}
//
//	private List<Order> dateFilter(String patientQuery, String startDateS,
//			String finalDateS, ModelAndView mav) {
//		Date startDate = null;
//		Date finalDate = null;
//		try {
//			finalDate = Context.getDateFormat().parse(finalDateS);
//		} catch (ParseException e) {
//			finalDate = null;
//		}
//		try {
//			startDate = Context.getDateFormat().parse(startDateS);
//		} catch (ParseException e) {
//			startDate = null;
//		}
//
//		if (startDate != null && finalDate != null) {
//			str += "s.af(fd) " + startDate.after(finalDate) + "\n";
//			if (startDate.after(finalDate)) {
//				mav.addObject("error", "crossDate");
//				return null;
//			}
//		}
//
//		OrderService os = Context.getOrderService();
//		PatientService ps = Context.getPatientService();
//		List<Order> preMatchedOrders = os.getOrders(Order.class,
//				ps.getPatients(patientQuery), null, null, null, null,
//				Utils.getRadiologyOrderType());
//		List<Order> matchedOrders = new Vector<Order>();
//
//		try {
//		if (startDate == null && finalDate == null) {
//			matchedOrders = preMatchedOrders;
//		} else if (startDate == null && finalDate != null)
//			for (Order order : preMatchedOrders) {
//				if (order.getStartDate() != null
//						&& order.getStartDate().compareTo(finalDate) <= 0) {
//					matchedOrders.add(order);
//				}
//			}
//		else if (finalDate == null && startDate != null)
//			for (Order order : preMatchedOrders) {
//				if (order.getStartDate() != null
//						&& order.getStartDate().compareTo(startDate) >= 0) {
//					matchedOrders.add(order);
//				}
//			}
//
//		else
//			for (Order order : preMatchedOrders) {
//				if (order.getStartDate() != null
//						&& order.getStartDate().compareTo(startDate) >= 0
//						&& order.getStartDate().compareTo(finalDate) <= 0) {
//					matchedOrders.add(order);
//				}
//			}
//		}catch(Exception e){
//			// TODO handle exception
//		}
//		return matchedOrders;
//	}
//
//}
