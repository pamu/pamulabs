package controllers

import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.api.libs.functional.syntax._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(""))
  }

  case class Contact(name: String, email: String, phone: String, message: String)
  def contactMe = Action(parse.json) { implicit request  =>
    implicit val contactReads: Reads[Contact] = (
        (JsPath \ "name").read[String] and
        (JsPath \ "email").read[String] and
        (JsPath \ "phone").read[String] and
        (JsPath \ "message").read[String]
      )(Contact.apply _)

    request.body.validate[Contact] match {
      case js: JsSuccess[Contact] =>
        val contact = js.get
        println(contact.toString)
        Ok(Json.obj("status" -> "success"))
      case error: JsError => Status(BAD_REQUEST)
    }
  }

}