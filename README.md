# **Email és file szerver quickie**
## A dockerben létrehozott email és file szervert a következő lépésekkel tudjátok aktiválni

1. Telepítsétek a [Dockert](https://www.docker.com/products/docker-desktop/)
2. A projekt docker mappájában nyissatok egy terminál ablakot.
3. Futtassátok a következő parancsot (**EZT A PARANCSOT CSAK EGY ALKALOMMAL KELL FUTTATNOTOK, NEM MINDEN INDÍTÁSKOR**):
```
docker-compose up 
```
4. Ezután futni fog minden szerver.
Ha ezzel megvagytok, rendelkezni fogtok egy futó Mail Serverrel. Tudnotok kell, hogy **ez csak egy dummy szerver**. Tehát nem küld ki valós emaileket, ellenben tesztelésre tökéletes. Nyomogassátok szét nyugodtan, ha találtok hibát írjatok! Ha meg akarjátok nézni hogy milyen kimenő üzenetek voltak, azt megtehetitek [a szerver dashboardján](http://localhost:8025) ha a container el van indítva.

A file szerver pedig real thing, ez lesz élesben is.

## Endpoint specifikáció

- Az email küldjés endpointja: http://localhost:8080/public/sendEmail 
- Method: Post
- Body: 
```
{
    "to":"küldő email címe",
    "from":"címzett email címe",
    "subject":"tárgy",
    "text":"Üzenet szövege"
}
```
