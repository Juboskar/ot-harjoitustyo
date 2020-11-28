# Arkkitehtuuri

## Luokkakaavio

Sovellus koostuu viidestä entiteetistä: käyttäjästä ja tämän pävittäisistä päästöistä (liikenne, ruoka ja kulutus), sekä vuoden kiinteistä päästöistä. Päästöistä talletetaan tiedot niiden eri lähteistä erillisinä kohtina. Tiedot tallennetaan Jpa -repositorioihin ja niitä käsitellään service luokilla.

Datamallin alustava luokkakaavio: 

<img src="https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/luokkakaavio.png" width="900">

## Kerrosarkkitehtuurin pakkausrakenne

Sovelluksen kerrosten pakkausrakenne on seuraava: 

<img src="https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/pakkauskaavio.png" width="400">

## Tärkeimpien tiedontallennustoimintojen sekvenssikaaviot

<img src="https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/kayttajan_luominen_ja_kirjautuminen.png" width="900">
<img src="https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/Kuvat/paivittaisten_paastojen_laskeminen.png" width="900">
