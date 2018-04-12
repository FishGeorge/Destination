package server;
import db.CreateDB;
import com.opensymphony.xwork2.ActionSupport;
/*Author:Maugham(刘瑞琦)
 * Time:2018-4-3
 * Tele:15764949052
 * QQ:3320089237
 * 
 * */
public class CreateDBAction extends ActionSupport{
		public String execute() throws Exception{
			CreateDB.createDBAuto();
			return "suc";
		}
}
