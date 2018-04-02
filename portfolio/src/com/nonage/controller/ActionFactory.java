package com.nonage.controller;

import com.nonage.admin.AdminIndexAction;
import com.nonage.admin.AdminLoginAction;
import com.nonage.admin.AdminLogoutAction;
import com.nonage.admin.AdminMemberListAction;
import com.nonage.admin.AdminOrderSaveAction;
import com.nonage.admin.AdminOrderlListAction;
import com.nonage.admin.AdminProductDetailAction;
import com.nonage.admin.AdminProductListAction;
import com.nonage.admin.AdminProductUpdateAction;
import com.nonage.admin.AdminProductUpdateFormAction;
import com.nonage.admin.AdminProductWriteAction;
import com.nonage.admin.AdminProductWriteFormAction;
import com.nonage.admin.AdminQnaDetailAction;
import com.nonage.admin.AdminQnaListAction;
import com.nonage.admin.AdminQnaResaveAction;
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
import com.nonage.controller.action.MyPageAction;
import com.nonage.controller.action.OrderAllAction;
import com.nonage.controller.action.OrderDetailAction;
import com.nonage.controller.action.OrderInsertAction;
import com.nonage.controller.action.OrderListAction;
import com.nonage.controller.action.ProductDetailAction;
import com.nonage.controller.action.ProductKindAction;
import com.nonage.controller.action.QnaListAction;
import com.nonage.controller.action.QnaViewAction;
import com.nonage.controller.action.QnaWriteAction;
import com.nonage.controller.action.QnaWriteFormAction;
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
	    } else if(command.equals("order_insert")) {
	    	action = new OrderInsertAction();
	    } else if(command.equals("order_list")) {
	    	action = new OrderListAction();
	    } else if(command.equals("mypage")) {
	    	action = new MyPageAction();
	    } else if(command.equals("order_detail")) {
	    	action = new OrderDetailAction();
	    } else if(command.equals("order_all")) {
	    	action = new OrderAllAction();
	    }  else if(command.equals("qna_list")) {
	    	action = new QnaListAction();
	    }	else if(command.equals("qna_write_form")) {
	    	action = new QnaWriteFormAction();
	    }	else if(command.equals("qna_write")) {
	    	action = new QnaWriteAction();
	    } 	else if(command.equals("qna_view")) {
	    	action = new QnaViewAction();
	    }	
		//관리자
		//관리
	    else if(command.equals("admin_login_form")) { //관리자 로그인 화면
	    	action = new AdminIndexAction();
	    }else if(command.equals("admin_login")) { //관리자 로그인
	    	action = new AdminLoginAction(); 
	    }else if(command.equals("admin_logout")) { //관리자 로그아웃
	    	action = new AdminLogoutAction();
	    }else if(command.equals("admin_product_list")) {
	    	action = new AdminProductListAction();
	    }else if (command.equals("admin_product_detail")) {
		      action = new AdminProductDetailAction();
	    }else if (command.equals("admin_product_write_form")) {
	        action = new AdminProductWriteFormAction();
	    }else if (command.equals("admin_product_write")) {
	        action = new AdminProductWriteAction();
	    }else if (command.equals("admin_product_update_form")) {
	    	action = new AdminProductUpdateFormAction();
	    }else if(command.equals("admin_product_update")) {
	    	action = new AdminProductUpdateAction();
	    }else if(command.equals("admin_order_list")) {
	    	action = new AdminOrderlListAction();
	    }else if(command.equals("admin_order_save")) {
	    	action = new AdminOrderSaveAction();
	    }else if(command.equals("admin_member_list")) {
	    	action = new AdminMemberListAction();
	    }else if(command.equals("admin_qna_list")) {
	    	action = new AdminQnaListAction();
	    }else if(command.equals("admin_qna_detail")) {
	    	action = new AdminQnaDetailAction();
	    }else if(command.equals("admin_qna_repsave")) {
	    	action = new AdminQnaResaveAction();
	    }
	    	
		
		return action;
	}
}
