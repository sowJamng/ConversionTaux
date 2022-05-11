

Le repertoire session du serveur sans bean.

1;Creer un compte
2.Supprimer un compte
3.Modifier un compte client
4. convertir taux
unidirection Onetomany => table de jointure
unidirection manyTone => clé etrangere

puisque le client s'est connecté du coup on a besoin de grader l'etat du client on utilse statefull car on a besin de grader
les informations qu'il est bien connceté pour lui donner ses services du coup on a besoin de conserver l'etat que l'abonné
s'est connecté avant d'ajouter un favori ou de faire une conversion favori de favorie du coup stateless ne convient pas dans
Ce cas mais le fait d'utiliser une seule Bean statefull une instance de bean par sollicitation de client ,chaque client
Son instance et  meme quand le client n'est pas connecté on rique de leur donné un instance
alors qu'on a vraimlent pas besoin de donner une instance à tout le monde voir ce dont on a pas besoin de garder leur etat ,
on risque de le faire pour rien un stateless est suffisant pour les non connecté ca amene juste une consommation inutile de ressource
merger l'entité car l'entite est a letat detache ,il faut le merger pour synchroniser l'instance abonneEntity .
pour verifier que l'entité n'est pas connecté il faut comparer l'instance à null car on peut conserver l'entité abonne  linstance n'est pas null