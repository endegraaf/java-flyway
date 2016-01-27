/* update the contacts column */
ALTER TABLE contacts MODIFY COLUMN contacts varchar(1024);

/* Create the remarks column */
ALTER TABLE contacts ADD COLUMN remarks varchar(1024);
