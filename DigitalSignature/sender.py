import socket


def rsa_generate_keys():
    p = 1170164324852094324459572433574667
    q = 19455398279217336617
    e = 65537
    f = (p - 1) * (q - 1)
    d = pow(e, -1, f)
    n = p * q
    return {"open_key": {"e": e, "n": n}, "secret_key": {"d": d, "n": n}}  # (e, n) - open key (d, n) - secret key


def encrypt(p, open_key):
    p2 = int((''.join(format(ord(i), '08b') for i in p).encode()), 2)
    cypher = pow(p2, open_key["e"], open_key["n"])
    return cypher


def decrypt(cypher, secret_key):
    return pow(cypher, secret_key["d"], secret_key["n"])


def connect():
    host = 'local host'
    port = 5000
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(('', port))
    s.listen(1)
    c, addr = s.accept()
    print("CONNECTION FROM:", str(addr))
    return c


def send(c, msg):
    c.send(msg)


def close(c):
    c.close()


if __name__ == '__main__':
    plaintext = "Hello, Bob"
    my_keys = rsa_generate_keys()
    cypher = encrypt(plaintext, my_keys["open_key"])
    print("Secret key for the Bob: " + str(my_keys["secret_key"]))
    m1 = format(cypher, 'b').encode()
    c = connect()
    send(c, m1)
    close(c)
