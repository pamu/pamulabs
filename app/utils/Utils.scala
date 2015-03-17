package utils

/**
 * Created by android on 18/3/15.
 */
object Utils {
  def htmlBody(name: String, email: String, phno: String, message: String): String =
    s"""
      |<p style="font-family:arial;">
      |I am $name
      |
      |Contact Details
      |Email $email
      |Phno $phno
      |
      |$message
      |</p>
    """.stripMargin
}