import requests

def query(i):
    return (requests.get("https://www.vle.cam.ac.uk/mod/url/view.php?id=23-"+str(i)+"2")).status_code != 404
for i in range(1,10000):
    if(query(i)):
        print i
        break
    print str(i)+" failed"

