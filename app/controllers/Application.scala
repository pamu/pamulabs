package controllers

import constants.Constants
import global.Global
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.api.libs.functional.syntax._
import utils.Utils

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
        Global.emailActor ! actors.EmailActor.Email(Constants.email, Constants.email,
          s"Message from ${contact.name}", Utils.htmlBody(contact.name, contact.email,
            contact.phone, contact.message))
        Ok(Json.obj("status" -> "success"))
      case error: JsError => Status(BAD_REQUEST)
    }
  }

  def blog() = TODO

}