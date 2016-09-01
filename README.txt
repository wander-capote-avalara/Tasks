Siga esses passos para importar o banco, ou use o SQL gerado pelo pgAdmin III(bkplain.sql)!!!!!
PSQL CREATE DATABASE taskdb; <- Não esquecer do ";"
PSQL \c taskdb
PSQL \i caminho/deployDb.sql
Pronto para usar!
driver = "org.postgresql.Driver";
path = "jdbc:postgresql://localhost:5432/taskdb";
user = "postgres";
password = "root";
port:5432;
dbName:taskdb;
OBS:
1 - Na pasta bd-mysql, há o DER do banco de dados!
2 - Na pasta bd-postgresql, há os arquivos em sql!
3 - ARQUIVO PARA USAR NO PSQL -> deployDb.sql;
4 - ARQUIVO GERADO PELO POSGRESQL DE BACKUP -> bkplain.sql -> SQL GERADO PELO pgAdmin III;
5 - Projeto criado pelo Eclipse Mars;
6 - Versão do postgresql : 9.4.9;