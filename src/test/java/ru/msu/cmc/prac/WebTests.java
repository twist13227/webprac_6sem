package ru.msu.cmc.prac;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTests {

    private ChromeDriver ChromeDriverRightVersion() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }
    @Test
    void MainPage() {
        String rootTitle = "Главная страница";
        ChromeDriver driver = ChromeDriverRightVersion();
        driver.manage().window().setSize(new Dimension(1920, 1007));
        driver.get("http://localhost:8080/");
        assertEquals(rootTitle, driver.getTitle());
        driver.quit();
    }

    @Test
    void ChromiumTest() {
        String peopleTitle = "Список людей";
        String personTitle = "Информация о человеке";
        String editPersonTitle = "Редактировать информацию о человеке";
        String addPersonTitle = "Добавить нового человека";
        String residencesTitle = "Список мест";
        String residenceTitle = "Информация о месте";
        String editResidenceTitle = "Редактировать место";
        String addResidenceTitle = "Добавить новое место жительства";
        String relationsTitle = "Список отношений";
        String relationTitle = "Информация об отношении";
        String editRelationTitle = "Редактировать отношение";
        String addRelationTitle = "Добавить новое отношение";
        ChromeDriver driver = ChromeDriverRightVersion();
        driver.manage().window().setSize(new Dimension(1920, 1007));
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("peopleListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(peopleTitle, driver.getTitle());
        WebElement table1 = driver.findElement(By.tagName("table"));
        List<WebElement> cells10 = table1.findElements(By.tagName("tr"));
        assertEquals(cells10.size(), 11);
        WebElement elem = cells10.get(10);
        List<WebElement> info = elem.findElements(By.tagName("td"));
        assertEquals(info.get(2).findElement(By.tagName("span")).getText(), "Курылкина Карина Альбертовна");
        assertEquals(info.get(3).findElement(By.tagName("span")).getText(), "Курылкин Геннадий Ярославович");
        // 4 | click | css=tr:nth-child(1) a > span |
        driver.findElement(By.cssSelector("tr:nth-child(1) a > span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(personTitle, driver.getTitle());
        WebElement personInfo = driver.findElement(By.id("personInfo"));
        List<WebElement> cells = personInfo.findElements(By.tagName("p"));
        assertEquals(cells.get(0).getText(), "Идентификационный номер: 1");
        assertEquals(cells.get(1).getText(), "ФИО: Тютиков Эдуард Леонидович");
        assertEquals(cells.get(2).getText(), "Пол: м");
        assertEquals(cells.get(3).getText(), "Годы жизни: 1975-06-05 - н.в.");
        assertEquals(cells.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells.get(5).getText(), "Краткая характеристика: Работает программистом в Яндексе. Домосед");
        List<WebElement> placeForPerson = cells.get(6).findElements(By.tagName("a"));
        assertEquals(placeForPerson.size(), 2);
        assertEquals(placeForPerson.get(0).findElement(By.tagName("span")).getText(), "Россия, Москва, улица Пушкина, дом 5, кв. 50;");
        assertEquals(placeForPerson.get(1).findElement(By.tagName("span")).getText(), "Россия, Краснознаменск, улица Маркса, дом 37,");
        // 5 | click | id=peopleListLink |
        driver.findElement(By.id("peopleListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 6 | click | id=addPersonButton |
        driver.findElement(By.id("addPersonButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(addPersonTitle, driver.getTitle());
        // 7 | click | id=Id |
        driver.findElement(By.id("Id")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 8 | type | id=Id | 11
        driver.findElement(By.id("Id")).sendKeys("11");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 9 | click | id=surname |
        driver.findElement(By.id("surname")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 10 | type | id=surname | Никитин
        driver.findElement(By.id("surname")).sendKeys("Никитин");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 11 | click | id=name |
        driver.findElement(By.id("name")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 12 | type | id=name | Иван
        driver.findElement(By.id("name")).sendKeys("Иван");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 13 | click | id=patronymic |
        driver.findElement(By.id("patronymic")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 14 | type | id=patronymic | Абрамович
        driver.findElement(By.id("patronymic")).sendKeys("Абрамович");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 15 | click | id=gender |
        driver.findElement(By.id("gender")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 16 | type | id=gender | м
        driver.findElement(By.id("gender")).sendKeys("м");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 17 | click | id=birth_date |
        driver.findElement(By.id("birth_date")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 21 | type | id=birth_date | 2005-09-15
        driver.findElement(By.id("birth_date")).sendKeys("15-09-2005");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.id("death_date")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 22 | click | id=characteristics |
        driver.findElement(By.id("characteristics")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 23 | type | id=characteristics | Прилежный ученик, поступает в университет.
        driver.findElement(By.id("characteristics")).sendKeys("Тест хар-ка");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 24 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(peopleTitle, driver.getTitle());
        WebElement table2 = driver.findElement(By.tagName("table"));
        List<WebElement> cells11 = table2.findElements(By.tagName("tr"));
        assertEquals(cells11.size(), 12);
        // 25 | click | css=tr:nth-child(11) a > span |
        driver.findElement(By.cssSelector("tr:nth-child(11) a > span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement personInfo1 = driver.findElement(By.id("personInfo"));
        List<WebElement> cells1 = personInfo1.findElements(By.tagName("p"));
        assertEquals(cells1.get(0).getText(), "Идентификационный номер: 11");
        assertEquals(cells1.get(1).getText(), "ФИО: Никитин Иван Абрамович");
        assertEquals(cells1.get(2).getText(), "Пол: м");
        assertEquals(cells1.get(3).getText(), "Годы жизни: 2005-09-15 -");
        assertEquals(cells1.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells1.get(5).getText(), "Краткая характеристика: Тест хар-ка");
        // 26 | click | id=editButton |
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(editPersonTitle, driver.getTitle());
        // 27 | click | id=characteristics |
        driver.findElement(By.id("characteristics")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        // 28 | type | id=characteristics | Прилежный ученик, поступил в университет, учится на 1 курсе
        driver.findElement(By.id("characteristics")).sendKeys(" 2");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 29 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(peopleTitle, driver.getTitle());
        // 30 | click | css=tr:nth-child(11) a > span |
        driver.findElement(By.cssSelector("tr:nth-child(11) a > span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 31 | click | id=deleteButton |
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(peopleTitle, driver.getTitle());
        WebElement table3 = driver.findElement(By.tagName("table"));
        List<WebElement> cells12 = table3.findElements(By.tagName("tr"));
        assertEquals(cells12.size(), 11);
        // 32 | click | css=tr:nth-child(10) > td:nth-child(3) span |
        driver.findElement(By.cssSelector("tr:nth-child(10) > td:nth-child(3) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(personTitle, driver.getTitle());
        WebElement personInfo2 = driver.findElement(By.id("personInfo"));
        List<WebElement> cells2 = personInfo2.findElements(By.tagName("p"));
        assertEquals(cells2.get(0).getText(), "Идентификационный номер: 2");
        assertEquals(cells2.get(1).getText(), "ФИО: Курылкина Карина Альбертовна");
        assertEquals(cells2.get(2).getText(), "Пол: ж");
        assertEquals(cells2.get(3).getText(), "Годы жизни: 1970-11-08 - н.в.");
        assertEquals(cells2.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells2.get(5).getText(), "Краткая характеристика: Любящая жена и мать, работает вместе с мужем в технической академии Росатома");
        List<WebElement> placeForPerson2 = cells2.get(6).findElements(By.tagName("a"));
        assertEquals(placeForPerson2.size(), 2);
        assertEquals(placeForPerson2.get(0).findElement(By.tagName("span")).getText(), "Россия, Санкт-Петербург, улица Лермонтова, дом 53, кв. 10;");
        assertEquals(placeForPerson2.get(1).findElement(By.tagName("span")).getText(), "Россия, Пушкино, улица Красная, дом 4,");
        // 37 | click | id=peopleListLink |
        driver.findElement(By.id("peopleListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(peopleTitle, driver.getTitle());
        // 38 | click | id=residencesListLink |
        driver.findElement(By.id("residencesListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        WebElement table11 = driver.findElement(By.tagName("table"));
        List<WebElement> cells15 = table11.findElements(By.tagName("tr"));
        assertEquals(cells15.size(), 11);
        // 39 | click | id=addResidenceButton |
        driver.findElement(By.id("addResidenceButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(addResidenceTitle, driver.getTitle());
        // 41 | click | id=Id |
        driver.findElement(By.id("Id")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 42 | type | id=Id | 11
        driver.findElement(By.id("Id")).sendKeys("11");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 43 | click | id=country |
        driver.findElement(By.id("country")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 44 | type | id=country | Аргентина
        driver.findElement(By.id("country")).sendKeys("Аргентина");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 45 | click | id=town |
        driver.findElement(By.id("town")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 46 | type | id=town | Буэнос-Айрес
        driver.findElement(By.id("town")).sendKeys("Буэнос-Айрес");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 47 | click | id=address |
        driver.findElement(By.id("address")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 48 | type | id=address | ул. Альвареза, д.10, кв.90
        driver.findElement(By.id("address")).sendKeys("Тест адрес");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 49 | click | id=description |
        driver.findElement(By.id("description")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 50 | type | id=description | Новая квартира семьи после отъезда зарубеж
        driver.findElement(By.id("description")).sendKeys("Новая квартира семьи после отъезда зарубеж");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 51 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        WebElement table31 = driver.findElement(By.tagName("table"));
        List<WebElement> cells35 = table31.findElements(By.tagName("tr"));
        assertEquals(cells35.size(), 12);
        // 52 | click | css=tr:nth-child(11) span |
        driver.findElement(By.cssSelector("tr:nth-child(11) span")).click();
        assertEquals(residenceTitle, driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement residenceInfo2 = driver.findElement(By.id("placeInfo"));
        List<WebElement> cells52 = residenceInfo2.findElements(By.tagName("p"));
        assertEquals(cells52.get(0).getText(), "Адрес: Аргентина, Буэнос-Айрес, Тест адрес");
        assertEquals(cells52.get(1).getText(), "Описание: Новая квартира семьи после отъезда зарубеж");
        List<WebElement> peopleForResidence2 = cells52.get(2).findElements(By.tagName("a"));
        assertEquals(peopleForResidence2.size(), 0);
        // 53 | click | id=editButton |
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(editResidenceTitle, driver.getTitle());
        // 54 | click | id=description |
        driver.findElement(By.id("description")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 55 | click | id=address |
        driver.findElement(By.id("address")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        // 56 | type | id=address | ул. Альвареза, д.10, кв.95
        driver.findElement(By.id("address")).sendKeys(" 2");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 57 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        // 58 | click | css=tr:nth-child(11) span |
        driver.findElement(By.cssSelector("tr:nth-child(11) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residenceTitle, driver.getTitle());
        WebElement residenceInfo3 = driver.findElement(By.id("placeInfo"));
        List<WebElement> cells53 = residenceInfo3.findElements(By.tagName("p"));
        assertEquals(cells53.get(0).getText(), "Адрес: Аргентина, Буэнос-Айрес, Тест адрес 2");
        // 59 | click | id=deleteButton |
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        WebElement table21 = driver.findElement(By.tagName("table"));
        List<WebElement> cells25 = table21.findElements(By.tagName("tr"));
        assertEquals(cells25.size(), 11);
        // 60 | click | css=tr:nth-child(4) span |
        driver.findElement(By.cssSelector("tr:nth-child(4) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residenceTitle, driver.getTitle());
        WebElement residenceInfo4 = driver.findElement(By.id("placeInfo"));
        List<WebElement> cells4 = residenceInfo4.findElements(By.tagName("p"));
        assertEquals(cells4.get(0).getText(), "Адрес: Россия, Новосибирск, улица Чехова, дом 12, кв. 47");
        assertEquals(cells4.get(1).getText(), "Описание: Квартира в спальном районе Новосибирска");
        List<WebElement> peopleForResidence4 = cells4.get(2).findElements(By.tagName("a"));
        assertEquals(peopleForResidence4.size(), 2);
        assertEquals(peopleForResidence4.get(0).findElement(By.tagName("span")).getText(), "Бабина Марина Казимировна,");
        assertEquals(peopleForResidence4.get(1).findElement(By.tagName("span")).getText(), "Бабин Вадим Олегович");
        // 61 | click | id=residencesListLink |
        driver.findElement(By.id("residencesListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        // 62 | click | id=relationsListLink |
        driver.findElement(By.id("relationsListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        // 63 | click | id=residencesListLink |
        driver.findElement(By.id("residencesListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        // 64 | click | css=tr:nth-child(5) span |
        driver.findElement(By.cssSelector("tr:nth-child(5) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residenceTitle, driver.getTitle());
        // 65 | click | css=a:nth-child(1) > span |
        driver.findElement(By.cssSelector("a:nth-child(1) > span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(personTitle, driver.getTitle());
        WebElement personInfo7 = driver.findElement(By.id("personInfo"));
        List<WebElement> cells9 = personInfo7.findElements(By.tagName("p"));
        assertEquals(cells9.get(0).getText(), "Идентификационный номер: 7");
        assertEquals(cells9.get(1).getText(), "ФИО: Носова Римма Олеговна");
        assertEquals(cells9.get(2).getText(), "Пол: ж");
        assertEquals(cells9.get(3).getText(), "Годы жизни: 1985-04-18 - н.в.");
        assertEquals(cells9.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells9.get(5).getText(), "Краткая характеристика: Жена бизнесмена, домохозяйка");
        List<WebElement> placeForPerson9 = cells9.get(6).findElements(By.tagName("a"));
        assertEquals(placeForPerson9.size(), 2);
        assertEquals(placeForPerson9.get(0).findElement(By.tagName("span")).getText(), "Россия, Обнинск, улица Ленина, дом 20, кв. 36;");
        assertEquals(placeForPerson9.get(1).findElement(By.tagName("span")).getText(), "Россия, Жуков, улица Зеленая, дом 8,");
        // 66 | click | id=residencesListLink |
        driver.findElement(By.id("residencesListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(residencesTitle, driver.getTitle());
        // 67 | click | id=relationsListLink |
        driver.findElement(By.id("relationsListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        WebElement table71 = driver.findElement(By.tagName("table"));
        List<WebElement> cells75 = table71.findElements(By.tagName("tr"));
        assertEquals(cells75.size(), 15);
        // 68 | click | css=tr:nth-child(3) > td:nth-child(1) span |
        driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(1) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationTitle, driver.getTitle());
        WebElement relationInfo7 = driver.findElement(By.id("relationInfo"));
        List<WebElement> cells97 = relationInfo7.findElements(By.tagName("p"));
        assertEquals(cells97.get(0).getText(), "Идентификационный номер: 3");
        assertEquals(cells97.get(1).getText(), "ФИО первого человека: Курылкина Карина Альбертовна");
        assertEquals(cells97.get(2).getText(), "ФИО второго человека: Курылкин Эмиль Геннадьевич");
        assertEquals(cells97.get(3).getText(), "Тип отношений: PARENT");
        assertEquals(cells97.get(4).getText(), "Подробности: Единственный сын");
        // 69 | click | id=relationsListLink |
        driver.findElement(By.id("relationsListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        // 70 | click | id=addRelationButton |
        driver.findElement(By.id("addRelationButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(addRelationTitle, driver.getTitle());
        // 71 | click | id=Id |
        driver.findElement(By.id("Id")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 72 | type | id=Id | 15
        driver.findElement(By.id("Id")).sendKeys("15");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 73 | click | id=first_person_id |
        driver.findElement(By.id("first_person_id")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 74 | type | id=first_person_id | 9
        driver.findElement(By.id("first_person_id")).sendKeys("9");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 75 | click | id=second_person_id |
        driver.findElement(By.id("second_person_id")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 76 | type | id=second_person_id | 10
        driver.findElement(By.id("second_person_id")).sendKeys("10");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 77 | select | id=relation_type | label=Внебрачный ребёнок
        WebElement dropdown = driver.findElement(By.id("relation_type"));
        dropdown.findElement(By.xpath("//select[@class=\"gwt-ListBox marginbelow\"]/option[contains(text(), 'Внебрачный ребёнок')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 78 | click | css=option:nth-child(6) |
        driver.findElement(By.cssSelector("option:nth-child(6)")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 79 | click | id=information |
        driver.findElement(By.id("information")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 80 | type | id=information | Внебрачный ребёнок
        driver.findElement(By.id("information")).sendKeys("Тест инфо");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // 81 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        WebElement table111 = driver.findElement(By.tagName("table"));
        List<WebElement> cells115 = table111.findElements(By.tagName("tr"));
        assertEquals(cells115.size(), 16);
        // 82 | click | css=tr:nth-child(15) > td:nth-child(2) span |
        driver.findElement(By.cssSelector("tr:nth-child(15) > td:nth-child(2) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(personTitle, driver.getTitle());
        // 83 | click | id=relationsListLink |
        driver.findElement(By.id("relationsListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        // 84 | click | css=tr:nth-child(15) > td:nth-child(3) span |
        driver.findElement(By.cssSelector("tr:nth-child(15) > td:nth-child(3) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(personTitle, driver.getTitle());
        WebElement personInfo11 = driver.findElement(By.id("personInfo"));
        List<WebElement> cells19 = personInfo11.findElements(By.tagName("p"));
        assertEquals(cells19.get(0).getText(), "Идентификационный номер: 10");
        assertEquals(cells19.get(1).getText(), "ФИО: Курылкин Эмиль Геннадьевич");
        assertEquals(cells19.get(2).getText(), "Пол: м");
        assertEquals(cells19.get(3).getText(), "Годы жизни: 2001-10-09 - н.в.");
        assertEquals(cells19.get(4).getText(), "Законнорожденный: да");
        assertEquals(cells19.get(5).getText(), "Краткая характеристика: Студент, учится на ВМК МГУ");
        List<WebElement> placeForPerson19 = cells19.get(6).findElements(By.tagName("a"));
        assertEquals(placeForPerson19.size(), 1);
        assertEquals(placeForPerson19.get(0).findElement(By.tagName("span")).getText(), "Россия, Москва, Ломоносовский проспект, дом 27, корпус 11");
        // 85 | click | id=relationsListLink |
        driver.findElement(By.id("relationsListLink")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        // 86 | click | css=tr:nth-child(15) > td:nth-child(1) span |
        driver.findElement(By.cssSelector("tr:nth-child(15) > td:nth-child(1) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationTitle, driver.getTitle());
        WebElement relationInfo9 = driver.findElement(By.id("relationInfo"));
        List<WebElement> cells99 = relationInfo9.findElements(By.tagName("p"));
        assertEquals(cells99.get(0).getText(), "Идентификационный номер: 15");
        assertEquals(cells99.get(1).getText(), "ФИО первого человека: Гавшина Юлиана Васильевна");
        assertEquals(cells99.get(2).getText(), "ФИО второго человека: Курылкин Эмиль Геннадьевич");
        assertEquals(cells99.get(3).getText(), "Тип отношений: BASTARD");
        assertEquals(cells99.get(4).getText(), "Подробности: Тест инфо");
        // 87 | click | id=editButton |
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        assertEquals(editRelationTitle, driver.getTitle());
        // 88 | click | id=information |
        driver.findElement(By.id("information")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        // 89 | type | id=information | 5
        driver.findElement(By.id("information")).sendKeys(" 2");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        // 90 | click | css=.btn |
        driver.findElement(By.cssSelector(".btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        // 91 | click | css=tr:nth-child(15) > td:nth-child(1) span |
        driver.findElement(By.cssSelector("tr:nth-child(15) > td:nth-child(1) span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationTitle, driver.getTitle());
        WebElement relationInfo91 = driver.findElement(By.id("relationInfo"));
        List<WebElement> cells991 = relationInfo91.findElements(By.tagName("p"));
        assertEquals(cells991.get(4).getText(), "Подробности: Тест инфо 2");
        // 92 | click | id=deleteButton |
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals(relationsTitle, driver.getTitle());
        WebElement table211 = driver.findElement(By.tagName("table"));
        List<WebElement> cells215 = table211.findElements(By.tagName("tr"));
        assertEquals(cells215.size(), 15);
        // 93 | click | id=github |
        driver.findElement(By.id("github")).click();
        driver.quit();
    }
}
