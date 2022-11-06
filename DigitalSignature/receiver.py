import socket
from sender import decrypt


secret_key = {"d": 6115910474074527915951391025713746129506756376936801, "n": 22766012992128952405333876335087361995777668342681539}
host = 'local host'
port = 5000

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.connect(('127.0.0.1', port))

msg = s.recv(1024)

while msg:
    print('Received:')
    m1 = format(decrypt(int(msg.decode(), 2), secret_key), 'b')
    m2 = [chr(int(m1[i:i + 7], 2)) for i in range(0, len(m1), 8)]
    m3 = ""
    for i in m2:
        m3 += i
    print(m3)
    msg = s.recv(1024)

s.close()