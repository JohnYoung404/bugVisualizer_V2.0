import socket
import os
import urllib

def dirlist(request):
   r=['<ul class="jqueryFileTree" style="display: none;">']
   try:
       r=['<ul class="jqueryFileTree" style="display: none;">']
       d=urllib.unquote(request.POST.get('dir','c:\\temp'))
       for f in os.listdir(d):
           ff=os.path.join(d,f)
           if os.path.isdir(ff):
               r.append('<li class="directory collapsed"><a rel="%s/">%s</a></li>' % (ff,f))
           else:
               e=os.path.splitext(f)[1][1:] # get .ext and remove dot
               r.append('<li class="file ext_%s"><a rel="%s">%s</a></li>' % (e,ff,f))
       r.append('</ul>')
   except Exception as e:
       r.append('Could not load directory: %s' % str(e))
   r.append('</ul>')
   return HttpResponse(''.join(r))

HOST, PORT = '', 8888
listen_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
listen_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
listen_socket.bind((HOST, PORT))
listen_socket.listen(1)
print ('Serving HTTP on port %s ...' % PORT)
#while True:
#    client_connection, client_address = listen_socket.accept()
#    request = client_connection.recv(1024)
#    print (request)
# 
#    http_response = """
#HTTP/1.1 200 OK
#
#Hello, World!
#"""
#    client_connection.sendall(http_response.encode())
#    client_connection.close()

class MyHandler(BaseRequestHandler):
    def handle(self):
        print('connected from:', self.client_address)
        data = '[%s]%s' % (ctime(), self.request.recv(BUFSIZE).decode('UTF-8'))
        self.request.send(bytes(data, 'UTF-8'))

tcpSer = TCPServer(ADDR, MyHandler)
print('...waiting fo connection...')
tcpSer.serve_forever()
