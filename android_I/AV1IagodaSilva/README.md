# AV2 Eletiva II - Introdução a Android

## Pontos exercitados
- **Task Async (Loader)** : 
    - Usado em um DbWorker para persistir/atualizar os dados de um currículo
      SQLiteDb. 
    - Classe implementando AsyncTask: sae.infnet.al.edu.av1iagodasilva.workers.DbWorker
    - Uso: Utilizado na MainActivity na linha 168

- **Shared Preferences** :
    - Usado como um cache após o processo de criação de um Currículo ser completado.
    - Classe utilizando SharedPreferences: sae.infnet.al.edu.av1iagodasilva.persistence.cache.CacheRepository
    - Uso: Utilizado na MainActivity nas linhas 62, 120, 169
    
- **SQLite** :
    - Usado para persistência de dados após a criação de um Currículo.
    - Classe utilizando SQLite funcionalidades: sae.infnet.al.edu.av1iagodasilva.persistence.db.DbHelper ,
    sae.infnet.al.edu.av1iagodasilva.persistence.db.DbGateway , e sae.infnet.al.edu.av1iagodasilva.persistence.db.dao.CurriculumDAO
    - Uso: Utilizado na classe CurriculumDAO para upsert
    
- **Firebase** :
    - Uso do serviço de advertisement (admob)
    - Classe utilizando Firebase funcionalidades: sae.infnet.al.edu.av1iagodasilva.MainActivity e o arquivo
    de layout content_main.xml
    - Uso: Utilizado na classe MainActivity nas linhas 53-56
    
 - **Monetização** :
    - Utilização de app free com o uso de ads através do uso de admob.