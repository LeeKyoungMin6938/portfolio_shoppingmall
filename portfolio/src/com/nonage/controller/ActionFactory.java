package com.nonage.controller;

import com.nonage.controller.action.Action;
import com.nonage.controller.action.CartDeleteAction;
import com.nonage.controller.action.CartInsertAction;
import com.nonage.controller.action.CartListAction;
import com.nonage.controller.action.ContractAction;
import com.nonage.controller.action.FindZipNumAction;
import com.nonage.controller.action.IndexAction;
import com.nonage.controller.action.JoinAction;
import com.nonage.controller.action.JoinFormAction;
import com.nonage.controller.action.LoginAction;
import com.nonage.controller.action.LoginFormAction;
import com.nonage.controller.action.LogoutAction;
import com.nonage.controller.action.ProductDetailAction;
import com.nonage.controller.action.ProductKindAction;
import com.nonage.controller.action.idCheckFormAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	public ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : "+command);
		
		if(command.equals("index")) {
			action = new IndexAction();
		}else if(command.equals("product_detail")) {
			action = new ProductDetailAction();
		}else if(command.equals("catagory")) {
			action = new ProductKindAction();
		}else if(command.equals("contract")) {
			action = new ContractAction();
		}else if(command.equals("join_form")) {
			action = new JoinFormAction();
		}else if(command.equals("id_check_form")) {
			action = new idCheckFormAction();
		}else if(command.equals("find_zip_num")) { ////
			action = new FindZipNumAction();
		}else if (command.equals("join")) {
	        action = new JoinAction();
	    }else if (command.equals("login_form")) {
	        action = new LoginFormAction();
	    }else if (command.equals("login")) {
	        action = new LoginAction();
	    } else if (command.equals("logout")) {
	        action = new LogoutAction();
	    } else if (command.equals("cart_insert")) {
	    	action = new CartInsertAction();
	    } else if (command.equals("cart_list")) {
	    	action = new CartListAction();
	    } else if (command.equals("cart_delete")) {
	        action = new CartDeleteAction();
	    } 			
		
		
		return action;
	}
}
