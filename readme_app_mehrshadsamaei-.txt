The main services of program is in ‘service’ package under name of ‘transferservice’ and 
‘transferserviceimple’

Noted: the mentioned services test have been properly written in application’s test section

The program includes a base package which contains repeating operation for each entity or service or dto

In this program Mysql , map struct, spring data, lombok have been used for better readability.

As long as all NTTs and services have been inherited , so the test is written for a service’s NTT ( the test was wallet with our personal choice)

In the test file, H2 database (in memory) have been used.

For login part , JWT have been used.
