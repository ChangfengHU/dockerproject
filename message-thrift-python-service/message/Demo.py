
import smtplib
from email.mime.text import MIMEText
from email.header import Header

sender = '2513120790@qq.com'
authCode = 'ozumtenfkdfpeaae'
def sendEmailMessage(email, message):
    print ("sendEmailMessage, email:"+email+", message:"+message)
    messageObj = MIMEText(message, "plain", "utf-8")
    messageObj['From'] = sender
    messageObj['To'] = email
    messageObj['Subject'] = Header('唯伊不可邮件', 'utf-8')
    try:
        smtpObj = smtplib.SMTP('smtp.qq.com')
        smtpObj.login(sender, authCode)
        smtpObj.sendmail(sender, [email], messageObj.as_string())
        print ("send mail success")
        return True
    except smtplib.SMTPException:
        print ("send mail failed!")
        print (smtplib.SMTPException)
        return False

sendEmailMessage("1457300208@qq.com","1212")