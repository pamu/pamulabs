package actors

import akka.actor.Status.{Failure, Success}
import akka.actor.{ActorLogging, Actor}
import constants.Constants
import org.apache.commons.mail.{DefaultAuthenticator, HtmlEmail}

import scala.concurrent.Future
import akka.pattern.pipe

/**
 * Created by android on 18/3/15.
 */

object EmailActor {
  case class Email(to: String, from: String, subject: String, htmlBody: String)
}

class EmailActor extends Actor with ActorLogging {

  import EmailActor._
  import context.dispatcher

  override def receive = {
    case Email(to, from, subject, htmlBody) =>
      val emailFuture = Future {
        val email = new HtmlEmail()
        email.setHostName(Constants.hostName)
        email.setSmtpPort(Constants.port);
        email.setAuthenticator(new DefaultAuthenticator(Constants.email, Constants.pass))
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true)
        email.setFrom(from);
        email.addTo(to)
        email.setSubject(subject)
        email.setHtmlMsg(htmlBody)
        email.send()
      }
      emailFuture pipeTo self

    case Success(status)    =>     log.info("Email Sent :) {}", status)
    case Failure(cause)     =>     log.info("Email failed {}", cause.getCause)
    case x: String          =>     log.info(x)
    case _                  =>     log.info("unknown message")
  }
}