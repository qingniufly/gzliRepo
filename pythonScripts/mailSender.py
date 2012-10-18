#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Copyright (c) - ligz <muzilimeng06@126.com>

"""This is a useful script for sending email.
You can specify the mail server and your email account in the config file "email_config.py"
the mail can contain text and multipart attachment.
"""

import email, os, sys, smtplib, datetime
from email.Header import Header
from email.MIMEText import MIMEText
from email.MIMEMultipart import MIMEMultipart
from email_config import HOST, PORT, ACCOUNT, PASSWORD, FROM, TO, CC, SUBJECT

class MailServer:
    def __init__(self, host=HOST, port=PORT, account=ACCOUNT, password=PASSWORD):
        self.server = smtplib.SMTP(host, port);
        self.server.ehlo()
        self.server.starttls()
        self.server.login(account, password)

    def send(self, mail):
        receiver = '%s,%s' % (mail['to'], mail['cc'])
        self.server.sendmail(mail['from'], receiver.split(','), mail.as_string())
        self.server.close()

def initMail(attaches, content='', mFrom=FROM, mTo=TO, mCc=CC, mSubject=SUBJECT): 
    mail = MIMEMultipart()
    mail['from'] = mFrom
    mail['to'] = mTo
    mail['cc'] = mCc
    mail['subject'] = Header(mSubject)
    mail['Date'] = email.Utils.formatdate()

    textContent = MIMEText(content, 'plain', 'utf-8')
    mail.attach(textContent)
        
    for attach in attaches:
        print '********* %s' % attach
        basename = os.path.basename(attach)
        att = MIMEText(open(basename, 'rb').read(), 'base64', 'gb2312')
        att['Content-Type'] = 'application/octet-stream'
        att['Content-Disposition'] = 'attachment; filename=%s' % basename.encode('gb2312')
        mail.attach(att)
        
    return mail

if __name__ == '__main__':
    server = MailServer()
    mail = initMail()
    server.send(mail)
