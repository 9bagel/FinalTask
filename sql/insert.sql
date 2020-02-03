insert into order_statuses(name) values
("New"),
("In progress"),
("Completed"),
("Paid"),
("Cancel");

INSERT into roles(name) values
("Administrator"),
("Customer");
INSERT into users(login, password, email, role_id) VALUE("admin", "$2a$10$MMTEpzi7jqAyxtHxP9SYpOGZV7RAOlmkLKIlS4NhuC7YI.t60lhkS", "test@epam.com", 1);

INSERT into service_types(name) values
("Haircut"),
("Paw care"),
("Washing and drying"),
("Combing out"),
("Claw trimming"),
("Ear cleaning"),
("Eye brushing"),
("SPA"),
("Teeth cleaning"),
("Taxi");


INSERT into services(type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price) values
(1,
"Hygienic haircut",
"Гигиеническая стрижка",
"Гігіенічная стрыжка",
"Cutting hair between the fingers and pads of the paws, as well as around the anus, genitals and on the inside of the thigh, shortening the claws, cleaning and releasing the dog’s ears from dead hair. Such procedures are usually done every two to four weeks, usually in order to keep the dog clean and tidy. Carried out with or without washing.",
"Выстригание шерсти между пальцами и подушечками лап, а также вокруг анального отверстия, половых органов и с внутренней стороны бедра, укорачивание когтей, чистка и освобождение ушей собаки от отмершей шерсти. Такие процедуры обычно делают каждые две-четыре недели, как правило, для того, чтобы содержать собаку в чистоте и порядке. Проводиться как с мытьем, так и без.",
"Выстрыганне воўны паміж пальцамі і падушачкамі лап, а таксама вакол анальнай адтуліны, палавых органаў і з унутранага боку сцягна, пакарочванне кіпцюроў, чыстка і вызваленне вушэй сабакі ад адмерлай воўны. Такія працэдуры звычайна робяць кожныя два-чатыры тыдні, як правіла, для таго, каб утрымліваць сабаку ў чысціні і парадку. Праводзіцца як з кіем, дык і без.",
18),
(1,
"Hygienic haircut completely under the machine",
"Гигиеническая стрижка полностью под машинку",
"Гігіенічная стрыжка цалкам пад машынку",
"This haircut is used in the hot season, or to facilitate care for skin diseases. If the dog has formed hard-to-sort collars, a clipper is also shown, as combing the collars is to torment the dog. Carried out with or without washing.",
"Эту стрижку применяют в жаркое время года, либо для облегчения ухода при заболеваниях кожи. Если у собаки образовались трудно разбираемые колтуны- также показана стрижка под машинку, так как расчёсывать колтуны - это мучить собаку. Проводиться как с мытьем, так и без.",
"Гэтую стрыжку ўжываюць у гарачую пару года, альбо для палягчэння сыходу пры захворваннях скуры. Калі ў сабакі утварыліся цяжка разбірайцеся колтуны- таксама паказана стрыжка пад машынку, так як расчёсывать каўтуны - гэта мучыць сабаку. Праводзіцца як з кіем, дык і без.",
20),
(1,
"Full complex",
"Полный комплекс",
"Поўны комплекс",
"Cleaning and emancipation of the dog’s ears from dead hair; claw shortening; cutting hair between fingers and paw pads, as well as around the anus, genitals and on the inside of the thigh, washing using professional cosmetics, which is selected individually for each dog; drying and styling; a model haircut in the lines of the breed, or a haircut taking into account the wishes of the owner.",
"Чистка и освобождение ушей собаки от отмершей шерсти; укорачивание когтей; выстригание шерсти между пальцами и подушечками лап, а также вокруг анального отверстия, половых органов и с внутренней стороны бедра, мытье с использованием профессиональной косметики, которая подбирается индивидуально для каждой собаки; сушка и укладка шерсти; модельная стрижка в линиях породы, либо стрижка с учетом пожеланий хозяина.",
"Чыстка і вызваленне вушэй сабакі ад адмерлай воўны; пакарочванне кіпцюроў; выстригание воўны паміж пальцамі і падушачкамі лап, а таксама вакол анальнай адтуліны, палавых органаў і з унутранага боку сцягна, мыццё з выкарыстаннем прафесійнай касметыкі, якая падбіраецца індывідуальна для кожнай сабакі; сушка і кладка воўны; мадэльная стрыжка ў лініях пароды, альбо стрыжка з улікам пажаданняў гаспадара.",
22),
(1,
"Hygienic cat grooming",
"Гигиеническая стрижка котов",
"Гігіенічная стрыжка катоў",
"It consists in the removal of excessively abundant, clumped or contaminated wool and the removal of tangles.",
"Заключается в удалении чрезмерно обильной, склочившейся или загрязненной шерсти и удалении колтунов.",
"Заключаецца ў выдаленні празмерна багатай, склочившейся або забруджанай воўны і выдаленні каўтуноў.",
26),
(1,
"Grooming cats under the machine (without washing)",
"Стрижка котов под машинку (без мытья)",
"Стрыжка катоў пад машынку (без мыцця)",
"Aesthetic haircut of cats taking into account the wishes of customers (for example: under a lion, dragon, etc.)",
"Эстетическая стрижка котов с учётом пожеланий клиентов (например: под льва, дракона и т.п.)",
"Эстэтычная стрыжка катоў з улікам пажаданняў кліентаў (напрыклад: пад льва, дракона і да т.п.)",
35),
(2,
"Dogs at the withers up to 35 cm",
"Собаки в холке до 35 см",
"Сабакі ў карку да 35 см",
"Caring for the paw pads in a dog is very important. Because they can often be prone to abrasions, cracks that can occur when in contact with hot asphalt in the summer and with salt, snow and low temperatures in the winter. We use your pet’s unique paw care product that contains lanolin, beeswax, vitamin E, as well as arnica and comfrey extract, and the lotion nourishes the paw pads and protects them from damage and irritation. This helps maintain flexibility, reduces the risk of injury, and relieves salt irritation in the winter. It perfectly moisturizes and nourishes, forming a protective layer to prevent it from drying out and accelerates the regeneration process. Improves and restores the softness of the pads of the feet of animals, on the corns of the elbows and on the coarsened tissue. Prevents slipping indoors and improves cushioning on grass.",
"Ухаживать за подушечками лап у собаки очень важно. Потому что они часто могут быть подвержены ссадинам, трещинам, которые могут произойти при контакте с горячим асфальтом летом и с солью, снегом и низкими температурами в зимний период. Мы используем уникальный препарат для ухода за лапами вашего питомца, который содержит ланолин, пчелиный воск, витамин Е, а также экстракт арники и окопника, лосьон питает подушечки лап и защищает их от повреждения и раздражения. Это помогает поддерживать гибкость, снижает риск получения травм и в зимнее время снимает раздражение от соли. Отлично увлажняет и питает, образуя защитный слой, чтобы предотвратить от высыхания и ускоряет процесс регенерации. Улучшает и восстанавливает мягкость подушечек лапок животных, на мозолях локтей и на огрубевшей ткани. Предотвращает скольжение в закрытых помещениях и улучшает амортизацию на травяных покрытиях.",
"Даглядаць за падушачкамі лап ў сабакі вельмі важна. Таму што яны часта могуць быць схільныя ранкамі, расколін, якія могуць адбыцца пры кантакце з гарачым асфальтам летам і з соллю, снегам і нізкімі тэмпературамі ў зімовы перыяд. Мы выкарыстоўваем унікальны прэпарат для сыходу за лапамі вашага гадаванца, які змяшчае ланалін, пчаліны воск, вітамін Е, а таксама экстракт купальніку і жывакоста, ласьён сілкуе падушачкі лап і абараняе іх ад пашкоджанні і раздражнення. Гэта дапамагае падтрымліваць гнуткасць, зніжае рызыку атрымання траўмаў і ў зімовы час здымае раздражненне ад солі. Выдатна увільгатняе і сілкуе, утвараючы ахоўны пласт, каб прадухіліць ад высыхання і паскарае працэс рэгенерацыі. Паляпшае і аднаўляе мяккасць падушачак лапак жывёл, на мазолях локцяў і на агрубелай тканіны. Прадухіляе слізгаценне ў закрытых памяшканнях і паляпшае амартызацыю на травяных пакрыццях.",
4),
(2,
"Dogs at the withers from 35-45 cm",
"Собаки в холке от 35-45 см",
"Сабакі ў карку ад 35-45 см",
"Caring for the paw pads in a dog is very important. Because they can often be prone to abrasions, cracks that can occur when in contact with hot asphalt in the summer and with salt, snow and low temperatures in the winter. We use your pet’s unique paw care product that contains lanolin, beeswax, vitamin E, as well as arnica and comfrey extract, and the lotion nourishes the paw pads and protects them from damage and irritation. This helps maintain flexibility, reduces the risk of injury, and relieves salt irritation in the winter. It perfectly moisturizes and nourishes, forming a protective layer to prevent it from drying out and accelerates the regeneration process. Improves and restores the softness of the pads of the feet of animals, on the corns of the elbows and on the coarsened tissue. Prevents slipping indoors and improves cushioning on grass.",
"Ухаживать за подушечками лап у собаки очень важно. Потому что они часто могут быть подвержены ссадинам, трещинам, которые могут произойти при контакте с горячим асфальтом летом и с солью, снегом и низкими температурами в зимний период. Мы используем уникальный препарат для ухода за лапами вашего питомца, который содержит ланолин, пчелиный воск, витамин Е, а также экстракт арники и окопника, лосьон питает подушечки лап и защищает их от повреждения и раздражения. Это помогает поддерживать гибкость, снижает риск получения травм и в зимнее время снимает раздражение от соли. Отлично увлажняет и питает, образуя защитный слой, чтобы предотвратить от высыхания и ускоряет процесс регенерации. Улучшает и восстанавливает мягкость подушечек лапок животных, на мозолях локтей и на огрубевшей ткани. Предотвращает скольжение в закрытых помещениях и улучшает амортизацию на травяных покрытиях.",
"Даглядаць за падушачкамі лап ў сабакі вельмі важна. Таму што яны часта могуць быць схільныя ранкамі, расколін, якія могуць адбыцца пры кантакце з гарачым асфальтам летам і з соллю, снегам і нізкімі тэмпературамі ў зімовы перыяд. Мы выкарыстоўваем унікальны прэпарат для сыходу за лапамі вашага гадаванца, які змяшчае ланалін, пчаліны воск, вітамін Е, а таксама экстракт купальніку і жывакоста, ласьён сілкуе падушачкі лап і абараняе іх ад пашкоджанні і раздражнення. Гэта дапамагае падтрымліваць гнуткасць, зніжае рызыку атрымання траўмаў і ў зімовы час здымае раздражненне ад солі. Выдатна увільгатняе і сілкуе, утвараючы ахоўны пласт, каб прадухіліць ад высыхання і паскарае працэс рэгенерацыі. Паляпшае і аднаўляе мяккасць падушачак лапак жывёл, на мазолях локцяў і на агрубелай тканіны. Прадухіляе слізгаценне ў закрытых памяшканнях і паляпшае амартызацыю на травяных пакрыццях.",
5),

(2,
"Dogs at the withers from 45 cm",
"Собаки в холке от 45 см",
"Сабакі ў карку ад 45 см",
"Caring for the paw pads in a dog is very important. Because they can often be prone to abrasions, cracks that can occur when in contact with hot asphalt in the summer and with salt, snow and low temperatures in the winter. We use your pet’s unique paw care product that contains lanolin, beeswax, vitamin E, as well as arnica and comfrey extract, and the lotion nourishes the paw pads and protects them from damage and irritation. This helps maintain flexibility, reduces the risk of injury, and relieves salt irritation in the winter. It perfectly moisturizes and nourishes, forming a protective layer to prevent it from drying out and accelerates the regeneration process. Improves and restores the softness of the pads of the feet of animals, on the corns of the elbows and on the coarsened tissue. Prevents slipping indoors and improves cushioning on grass.",
"Ухаживать за подушечками лап у собаки очень важно. Потому что они часто могут быть подвержены ссадинам, трещинам, которые могут произойти при контакте с горячим асфальтом летом и с солью, снегом и низкими температурами в зимний период. Мы используем уникальный препарат для ухода за лапами вашего питомца, который содержит ланолин, пчелиный воск, витамин Е, а также экстракт арники и окопника, лосьон питает подушечки лап и защищает их от повреждения и раздражения. Это помогает поддерживать гибкость, снижает риск получения травм и в зимнее время снимает раздражение от соли. Отлично увлажняет и питает, образуя защитный слой, чтобы предотвратить от высыхания и ускоряет процесс регенерации. Улучшает и восстанавливает мягкость подушечек лапок животных, на мозолях локтей и на огрубевшей ткани. Предотвращает скольжение в закрытых помещениях и улучшает амортизацию на травяных покрытиях.",
"Даглядаць за падушачкамі лап ў сабакі вельмі важна. Таму што яны часта могуць быць схільныя ранкамі, расколін, якія могуць адбыцца пры кантакце з гарачым асфальтам летам і з соллю, снегам і нізкімі тэмпературамі ў зімовы перыяд. Мы выкарыстоўваем унікальны прэпарат для сыходу за лапамі вашага гадаванца, які змяшчае ланалін, пчаліны воск, вітамін Е, а таксама экстракт купальніку і жывакоста, ласьён сілкуе падушачкі лап і абараняе іх ад пашкоджанні і раздражнення. Гэта дапамагае падтрымліваць гнуткасць, зніжае рызыку атрымання траўмаў і ў зімовы час здымае раздражненне ад солі. Выдатна увільгатняе і сілкуе, утвараючы ахоўны пласт, каб прадухіліць ад высыхання і паскарае працэс рэгенерацыі. Паляпшае і аднаўляе мяккасць падушачак лапак жывёл, на мазолях локцяў і на агрубелай тканіны. Прадухіляе слізгаценне ў закрытых памяшканнях і паляпшае амартызацыю на травяных пакрыццях.",
6),

(3,
"Washing and drying cats and dogs",
"Мытьё и сушка котов и собак",
"Мыццё і сушка катоў і сабак",
"Bathing a dog is a mandatory procedure that must be carried out regularly. This is not only prevention from skin diseases, but also the care of the hair and skin of dogs and cats. Your pet will feel much better with timely grooming, since regular combing and washing prevents tangling, the formation of tangles and improves blood circulation in the skin. We use only professional and high-quality cosmetics, individually selecting shampoo and conditioner for your pet.",
"Купание собаки – обязательная процедура, которую необходимо проводить регулярно. Это не только профилактика от кожных заболеваний, но и уход за шерстью и кожей собак, и котов. Ваш питомец будет чувствовать себя намного лучше при своевременном уходе, так как регулярное расчесывание и мытье предотвращает спутывание шерсти, образование колтунов и улучшает кровообращение в кожных покровах. Мы используем только профессиональную и высококачественную косметику, индивидуально подбирая шампунь и кондиционер для Вашего питомца.",
"Купанне сабакі - абавязковая працэдура, якую неабходна праводзіць рэгулярна. Гэта не толькі прафілактыка ад скурных захворванняў, але і догляд за поўсцю і скурай сабак, і катоў. Ваш гадаванец будзе адчуваць сябе нашмат лепш пры своечасовым сыходзе, так як рэгулярнае расчэсваннем і мыццё прадухіляе зблытвання воўны, адукацыю каўтуноў і паляпшае кровазварот у скурных пакровах. Мы выкарыстоўваем толькі прафесійную і высакаякасную касметыку, індывідуальна падбіраючы шампунь і кандыцыянер для Вашага гадаванца.",
17),

(4,
"Combing out of wool",
"Вычесывание из шерсти колтунов",
"Вычесывание з воўны каўтуноў",
"The cost of the service is 15 rubles / hour. Cutting the tangles under the machine from 3 to 6 rubles.",
"Стоимость услуги 15 рублей/час. Срезание колтунов под машинку от 3 до 6 рублей.",
"Кошт паслугі 15 рублёў / гадзіну. Зразанне каўтуноў пад машынку ад 3 да 6 рублёў.",
15),

(5,
"Claw trimming",
"Подстригание когтей",
"Подстригание кіпцюроў",
"It is especially important to monitor the condition of the claws, because too long claws prevent the dog from “collecting” the paw, as this affects the gait and general tone, and long claws can cause infections. Regarding cats, at home, the claws may become too long and cling to upholstered furniture, carpets. It is recommended to cut nails once a month or more often, without waiting for the time when the dog starts pounding on the parquet in the apartment, preventing them from sleeping or simply damaging the floor and walls of the furniture. In addition, you need to pay attention to the claws on the dewclaws (side) fingers. They are not susceptible to grinding and, growing too much, can grow into the skin than cause infection and lameness.",
"Особенно важно следить за состоянием когтей, поскольку слишком длинные когти мешают собаке «собрать» лапу, так как это отражается на походке и общем тонусе, а длинные когти могут стать причиной инфекций. Касательно котов, в домашних условиях, когти могут стать слишком длинными и цепляться за мягкую мебель, ковры. Стричь когти рекомендуется раз в месяц или чаще, не дожидаясь того времени, когда собака начнёт стучать ими по паркету в квартире мешая спать или попросту повреждать пол, стены мебель. Кроме этого, нужно уделять внимание когтям на прибылых (боковых) пальцах. Они не подвержены стачиванию и, отрастая слишком сильно, могут врасти в кожу, чем вызвать заражение и хромоту.",
"Асабліва важна сачыць за станам кіпцюроў, паколькі занадта доўгія кіпцюры перашкаджаюць сабаку «сабраць» лапу, так як гэта адбіваецца на хадзе і агульным тонусе, а доўгія кіпцюры могуць стаць прычынай інфекцый. Датычна катоў, у хатніх умовах, кіпцюры могуць стаць занадта доўгімі і чапляцца за мяккую мэблю, дываны. Стрыгчы кіпцюры рэкамендуецца раз у месяц ці часцей, не чакаючы таго часу, калі сабака пачне стукаць імі па паркеце ў кватэры замінаючы спаць або папросту пашкоджваць падлогу, сцены мэбля. Акрамя гэтага, трэба надаваць увагу кіпцюрах на прыбылых (бакавых) пальцах. Яны не схільныя стачиванию і, адгадаваць занадта моцна, могуць ўрасці ў скуру, каб ня выклікаць заражэнне і кульгавасць.",
5),

(6,
"Ear cleaning",
"Чистка ушей",
"Чыстка вушэй",
"In order to reduce the risk of problems with ear disease, prevention is needed in the first place. In a healthy dog, a small amount of dark brown discharge called “ear wax” may form in the ears. Normal discharge protects the surface of the ear canal and does not need to be removed daily. The procedure for cleaning and pulling out excess hair from the auricle is not very pleasant for your pet and many owners do not like to do this, being afraid to harm their pet. Our masters using special tools will do this procedure as quickly and painlessly as possible for your pet.",
"Для того, чтобы снизить риск возникновения проблем с заболеванием ушей, нужна, в первую очередь, профилактика. У здоровой собаки в ушах может образовываться небольшое количество выделений темно-коричневого цвета, которые называются «ушной серой». Нормальные выделения защищают поверхность слухового прохода, и их не нужно удалять ежедневно. Процедура чистки и вырывания лишних волос из ушной раковины не очень приятна для вашего любимца и многие хозяева не любят заниматься этим, боясь нанести вред своему питомцу. Наши мастера с помощью специальных средств сделают эту процедуру максимально быстро и безболезненно для Вашего питомца.",
"Для таго, каб знізіць рызыку ўзнікнення праблем з захворваннем вушэй, патрэбна, у першую чаргу, прафілактыка. У здаровай сабакі ў вушах можа ўтварацца невялікае колькасць выдзяленні цёмна-карычневага колеру, якія называюцца «вушной шэрай». Нармальныя выдзялення абараняюць паверхню слыхавога праходу, і іх не трэба выдаляць штодня. Працэдура чысткі і вырывання лішніх валасоў з вушной ракавіны не вельмі прыемная для вашага ўлюбёнца і многія гаспадары не любяць займацца гэтым, баючыся нанесці шкоду свайму выхаванцу. Нашы майстры з дапамогай спецыяльных сродкаў зробяць гэтую працэдуру максімальна хутка і бязбольна для Вашага гадаванца.",
4),

(7,
"Eye brushing",
"Чистка глаз",
"чыстка вачэй",
"Eye brushing",
"Чистка глаз",
"чыстка вачэй",
3),

(8,
"SPA treatments",
"SPA-процедуры",
"SPA-працэдуры",
"SPA treatments",
"SPA-процедуры",
"SPA-працэдуры",
7),

(9,
"Tartar and plaque removal using ultrasound (polished)",
"Удаление зубного камня и налёта с помощью ультразвука (с полировкой)",
"Выдаленне зубнога каменя і налёту з дапамогай ультрагуку (з паліроўкай)",
"Hygiene of the jaws of pets is an integral part of caring for any dog ​​or cat, because teeth are an important aspect of the health and appearance of the animal. The jaw or cat’s mouth needs special attention, especially during a tooth change (usually six to seven months old). in such a period, the animals begin to change their primary teeth in molars (permanent) and it is very important to monitor their normal growth and development, since the formation of a normal bite of a dog or cat will depend on this. For animals, it is important to pay attention to the color of the teeth, the formation of plaque on the teeth, the condition of the gums in the pet, the presence of foreign objects stuck in the teeth of the animal (parts of bones, sticks, etc.). If any abnormalities are found, the dog or cat is important show the veterinarian. Diseased teeth lead to serious consequences against which diseases such as fistula and periodontitis develop. Therefore, brushing your teeth is so important for your four-legged friend. Our masters will make brushing quickly and professionally. ",
"Гигиена пасти домашних питомцев – неотъемлемая часть ухода за любой собакой или кошкой, ведь зубы – это важный аспект здоровья и внешнего вида животного. Собачья или кошачья пасть требует особого внимания, особенно в период смены зубов (обычно шести-семи месячный возраст). Именно в такой период у животных начинают молочные зубы сменяться коренными (постоянными) и очень важно следить за их нормальным ростом и развитием, так как от этого будет зависеть формирование нормального прикуса собаки или кошки.Проводя самостоятельный осмотр пасти животного важно обращать внимание на цвет зубов, образование налета на зубах, состояние десен у питомца, наличие инородных, застрявших в зубах животного предметов (части костей, палок и т.д.). В случае обнаружения каких-то отклонений, собаку или кошку важно показать ветеринару. Больные зубы приводят к серьезным последствиям на фоне которых развиваются такие болезни как свищ и периодонтит. Поэтому чистка зубов так важна для Вашего четвероногого друга. Наши мастера сделают чистку зубов быстро и профессионально.",
"Гігіена пасвіць хатніх гадаванцаў - неад'емная частка догляду за любы сабакам або коткай, бо зубы - гэта важны аспект здароўя і вонкавага выгляду жывёлы. Сабачая або каціная пашча патрабуе асаблівай увагі, асабліва ў перыяд змены зубоў (звычайна шасці-сямі месячны узрост). Менавіта у такі перыяд у жывёл пачынаюць малочныя зубы змяняцца карэннымі (пастаяннымі) і вельмі важна сачыць за іх нармальным ростам і развіццём, бо ад гэтага будзе залежаць фарміраванне нармальнага прыкусу сабакі або кошки.Проводя самастойны агляд пасвіць ивотного важна звяртаць увагу на колер зубоў, адукацыя налёту на зубах, стан дзёсен у гадаванца, наяўнасць іншародных, якія затрымаліся ў зубах жывёльнага прадметаў (часткі костак, палак і г.д.). У выпадку выяўлення якіх-то адхіленняў, сабаку або котку важна паказаць ветэрынара. Хворыя зубы прыводзяць да сур'ёзных наступстваў на фоне якіх развіваюцца такія хваробы як свіршч і перыядантыт. Таму чыстка зубоў так важная для Вашага чацвераногага сябра. Нашы майстры зробяць чыстку зубоў хутка і прафесійна.",
50),

(9,
"Hygienic toothbrushing (paste + spray)",
"Гигиеническая чистка зубов (паста+спрей)",
"Гігіенічная чыстка зубоў (паста + спрэй)",
"For owners of dogs and cats! Brushing teeth for dogs and brushing teeth in cats should be carried out regularly and in a timely manner. Veterinarians recommend accustoming a pet to brushing teeth at an early age, since these hygienic measures are not pleasant for adult animals, if they are not accustomed to the cleaning procedure from childhood, they can cause special disturbances.",
"К сведению владельцев собак и кошек! Чистка зубов собаке и чистка зубов у кошек должна проводиться регулярно и своевременно. Ветеринары рекомендуют приучать питомца к чистке зубов в раннем возрасте, так как эти гигиенические мероприятия не из приятных для взрослых животных, если они не приучены к процедуре чистки с детства, они могут вызвать особые возмущения.",
"Да ведама ўладальнікаў сабак і катоў! Чыстка зубоў сабаку і чыстка зубоў у котак павінна праводзіцца рэгулярна і своечасова. Ветэрынары рэкамендуюць прывучаць гадаванца да чысткі зубоў у раннім узросце, так як гэтыя гігіенічныя мерапрыемствы не з прыемных для дарослых жывёл, калі яны не прывучаны да працэдуры чысткі з дзяцінства, яны могуць выклікаць асаблівыя абурэння.",
5),

(10,
"TAXI for your pet",
"TAXI для вашего питомца",
"TAXI для вашага гадаванца",
"We pick up the pet and take it back, depending on the district of the city of Minsk and the suburbs. You do not have time or opportunity to come to the salon, and your pet needs professional care, we took care of this too: departure of our professional driver for your pet and delivery back after all the procedures in our salon. Transportation is carried out in a specially equipped car and with absolute safety for the pet. At this time, you can safely do your favorite or important business without being distracted by a trip to the salon. Beauty salon for animals Manifik is the only salon in Minsk that provides this kind of service. We also leave for the village of Tarasovo, pos. Ratomka, pos. Borovlyany when ordering a full range of services.",
"Заберём питомца и отвезём обратно в зависимости от района города Минска и пригорода. У вас нет времени или возможности приехать в салон, а ваш питомец нуждается в профессиональном уходе, мы позаботились и об этом: выезд нашего профессионального водителя за вашим питомцем и доставка обратно после проведения всех процедур в нашем салоне. Перевозка осуществляется в специально оборудованном автомобиле и с абсолютной безопасностью для питомца. Вы в это время можете спокойно заниматься вашими любимыми или важными делами, не отвлекаясь на поездку в салон. Салон красоты для животных Манифик- это единственный салон в Минске, оказывающий такого рода услугу. Так же мы выезжаем в д. Тарасово, пос. Ратомка, пос. Боровляны при заказе полного комплекса услуг.",
"Забярэм гадаванца і адвязём назад у залежнасці ад раёна горада Мінска і прыгарада. У вас няма часу або магчымасці прыехаць у салон, а ваш гадаванец мае патрэбу ў прафесійным сыходзе, мы паклапаціліся і пра гэта: выезд нашага прафесійнага кіроўцы за вашым гадаванцам і дастаўка назад пасля правядзення ўсіх працэдур ў нашым салоне. Перавозка ажыццяўляецца ў спецыяльна абсталяваным аўтамабілі і з абсалютнай бяспекай для гадаванца. Вы ў гэты час можаце спакойна займацца вашымі любімымі або важнымі справамі, не адцягваючыся на паездку ў салон. Салон прыгажосці для жывёл Манифик - гэта адзіны салон у Мінску, які аказвае такога роду паслугу. Гэтак жа мы выязджаем у в. Тарасава, пас. Ратамка, пас. Бараўляны пры замове поўнага комплексу паслуг.",
7);