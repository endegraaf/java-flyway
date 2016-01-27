
DELIMITER //
DROP PROCEDURE IF EXISTS `sp_contacts_SELECT`//

CREATE PROCEDURE sp_contacts_SELECT
     (
        IN   p_id                 		INT(4)       	, 
        OUT  p_name               VARCHAR(30)   	, 
        OUT  p_contacts                VARCHAR(30)    	, 
        OUT  p_remarks                  VARCHAR(255)   	
     )
BEGIN 

    SELECT id							, 
           name                   , 
           contacts                    , 
           remarks						
    INTO   p_id                      	, 
           p_name                 , 
           p_contacts                  , 
           p_remarks                    
    FROM   contacts
    WHERE  id = p_id ; 

END//

DELIMITER ;

