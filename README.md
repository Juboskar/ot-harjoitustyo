# Ohjelmistotekniikka, harjoitustyö

## Päästöpäiväkirja

Päästöpäiväkirja on sovellus, jolla voi pitää päivittäistä kirjaa hiilipäästöistään ja tarkkailla päästöjen kertymistä. Sovellus on suunniteltu erityisesti se mielessä, että sitä käytettäisiin vuoden ajan, mutta mikään ei estä käyttämästä lyhyemmän ajan tai vain laskemaan yksittäisiä päiviä silloin tällöin. Tällöin kannattaa huomioida että osa päästöistä lasketaan kiinteinä vuosipäästöinä.

### Releaset

[Toinen Github -release (viikon 6 deadline)](https://github.com/Juboskar/ot-harjoitustyo/releases/tag/viikko6) (toimii linux cubblilla)

[Ensimmäinen Github -release (viikon 5 deadline)](https://github.com/Juboskar/ot-harjoitustyo/releases/tag/viikko5) (toimii linux cubblilla)

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Juboskar/ot-harjoitustyo/blob/master/Dokumentaatio/kayttoohje.md)

### Komentorivitoiminnot

Sovelluksen komentorivitoiminnot tulee suorittaa ohjelman juurikansiossa eli repositorion kansiossa Paastopaivakirja. 
Projektissa on käytössä mm. seuraavat komentorivitoiminnot:

- Ohjelma voidaan käynnistää komentorivillä komennolla ```mvn spring-boot:run```.
- Testit suoritetaan komennolla  ```mvn test```.
- Testikattavuusraportti luodaan komennolla ```mvn jacoco:report```.
 Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html.
- Jar -tiedosto generoidaan komennolla ```mvn package```.
- JavaDoc generoidaan komennolla ```mvn javadoc:javadoc```.
JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html.
- Tiedostoon [checkstyle.xml](https://github.com/Juboskar/ot-harjoitustyo/blob/master/Paastopaivakirja/checkstyle.xml) määritellyt checkstyle -tarkistukset suoritetaan komennolla ```mvn jxr:jxr checkstyle:checkstyle```
Checkstyle -virheitä voi tutkia avaamalla selaimella tiedosto _target/site/checkstyle.html_
