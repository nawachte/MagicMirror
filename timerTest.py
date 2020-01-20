import threading
import sys
def done():
    for i in range(1000):
        print("doneeeeeeeeeeeeeeeeeeeeeeeeeeee")
        sys.exit()

t = threading.Timer(5.0,done)
t.start()


def doNothing():
    pass

def wait():
    global t
    t2 = threading.Timer(1.0,pass)
    t2.start()
    while t.is_alive() == True:
        if t2.is_alive() == False:
            print("goingaaaa")
            t2 = threading.Timer(1.0,pass)
            t2.start()
    done()
    t = threading.Timer(5.0, done())
    t.start()
    wait()
wait()