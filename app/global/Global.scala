package global

import actors.EmailActor
import akka.actor.{Props, ActorSystem}
import play.api.{Logger, GlobalSettings, Application}

/**
 * Created by android on 18/3/15.
 */
object Global extends GlobalSettings {

  lazy val system = ActorSystem("ActorSystem")
  lazy val emailActor = system.actorOf(Props[EmailActor], "EmailActor")

  override def onStart(app: Application): Unit = {
    Logger.info("pamulabs started")
  }
  override def onStop(app: Application): Unit = {
    Logger.info("pamulabs stopped")
  }
}
