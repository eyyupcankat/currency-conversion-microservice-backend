### Proje Hakkında
Bu repo, Spring Boot tabanlı mikroservis mimarisi içerir. Projede iki işlevsel servis (kur verme ve kurla dönüştürme), bir API Geçidi, ayrıca merkezi konfigürasyon için bir Config Server ve servis keşfi için bir Naming Server (Eureka) vardır.

### Bileşenler
- `config-server`: Tüm servislerin konfigürasyonlarını merkezi bir kaynaktan (Git repo, dosya sistemi vb.) sağlar.
- `naming-server` (Eureka Server): Servis keşfi ve kayıt. Tüm mikroservisler bu sunucuya kaydolur ve birbirlerini isimle bulur.
- `api-gateway`: Dış trafiği mikroservislere yönlendirir, ortak cross-cutting ihtiyaçları merkezi yönetir. Örnek `LoggingFilter` içerir.
- `currency-exchange-service`: İki para birimi arasındaki kuru döner. Başlangıç verileri `data.sql` ile yüklenir.
- `currency-conversion-service`: Exchange servisinden aldığı kurla tutar dönüştürür (Feign/RestTemplate).

### Teknolojiler
- Java 17+
- Spring Boot (Web, Actuator)
- Spring Cloud: Config Server, Eureka Discovery, Gateway, OpenFeign/RestTemplate, LoadBalancer
- Maven
- H2

### Mimari Akış
1. Servisler açılışta `config-server` üzerinden konfigürasyonlarını çeker (örn. `spring.config.import=optional:configserver:`).
2. Servisler `naming-server` (Eureka) üzerine kayıt olur.
3. `api-gateway`, istekleri servis isimlerine yönlendirir.
4. `currency-conversion-service`, `currency-exchange-service`e servis adıyla (Eureka) veya gateway üzerinden çağrı yapar.
5. Sonuçlar istemciye gateway üzerinden döner.
