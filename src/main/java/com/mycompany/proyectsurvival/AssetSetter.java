
package com.mycompany.proyectsurvival;

import com.mycompany.proyectsurvival.EntitiesResourcesShelter.Tree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new Tree(1);
        gp.obj[0].worldX = 6*gp.size;
        gp.obj[0].worldY = 3*gp.size;
        
        gp.obj[1] = new Tree(2);
        gp.obj[1].worldX = 10*gp.size;
        gp.obj[1].worldY = 3*gp.size;

        gp.obj[2] = new Tree(2);
        gp.obj[2].worldX = 20*gp.size;
        gp.obj[2].worldY = 4*gp.size;
        
        gp.obj[3] = new Tree(3);
        gp.obj[3].worldX = 11*gp.size;
        gp.obj[3].worldY = 5*gp.size;
        
        gp.obj[4] = new Tree(3);
        gp.obj[4].worldX = 19*gp.size;
        gp.obj[4].worldY = 5*gp.size;
        
        gp.obj[5] = new Tree(2);
        gp.obj[5].worldX = 16*gp.size;
        gp.obj[5].worldY = 6*gp.size;
        
        gp.obj[6] = new Tree(2);
        gp.obj[6].worldX = 22*gp.size;
        gp.obj[6].worldY = 6*gp.size;

        gp.obj[7] = new Tree(1);
        gp.obj[7].worldX = 10*gp.size;
        gp.obj[7].worldY = 7*gp.size;
        
        gp.obj[8] = new Tree(1);
        gp.obj[8].worldX = 14*gp.size;
        gp.obj[8].worldY = 8*gp.size;
        
        gp.obj[9] = new Tree(1);
        gp.obj[9].worldX = 19*gp.size;
        gp.obj[9].worldY = 8*gp.size;

        gp.obj[10] = new Tree(2);
        gp.obj[10].worldX = 24*gp.size;
        gp.obj[10].worldY = 8*gp.size;
        
        gp.obj[11] = new Tree(3);
        gp.obj[11].worldX = 8*gp.size;
        gp.obj[11].worldY = 10*gp.size;

        gp.obj[12] = new Tree(3);
        gp.obj[12].worldX = 14*gp.size;
        gp.obj[12].worldY = 10*gp.size;
        
        gp.obj[13] = new Tree(1);
        gp.obj[13].worldX = 27*gp.size;
        gp.obj[13].worldY = 10*gp.size;
        
        gp.obj[14] = new Tree(3);
        gp.obj[14].worldX = 11*gp.size;
        gp.obj[14].worldY = 11*gp.size;

        gp.obj[15] = new Tree(2);
        gp.obj[15].worldX = 22*gp.size;
        gp.obj[15].worldY = 11*gp.size;
        
        gp.obj[16] = new Tree(2);
        gp.obj[16].worldX = 15*gp.size;
        gp.obj[16].worldY = 12*gp.size;

        gp.obj[17] = new Tree(1);
        gp.obj[17].worldX = 20*gp.size;
        gp.obj[17].worldY = 12*gp.size;
        
        gp.obj[18] = new Tree(1);
        gp.obj[18].worldX = 10*gp.size;
        gp.obj[18].worldY = 13*gp.size;
        
        gp.obj[19] = new Tree(2);
        gp.obj[19].worldX = 24*gp.size;
        gp.obj[19].worldY = 13*gp.size;
        
        gp.obj[20] = new Tree(2);
        gp.obj[20].worldX = 20*gp.size;
        gp.obj[20].worldY = 7*gp.size;
        
        gp.obj[21] = new Tree(2);
        gp.obj[21].worldX = 17*gp.size;
        gp.obj[21].worldY = 14*gp.size;

        gp.obj[22] = new Tree(2);
        gp.obj[22].worldX = 5*gp.size;
        gp.obj[22].worldY = 15*gp.size;
        
        gp.obj[23] = new Tree(1);
        gp.obj[23].worldX = 15*gp.size;
        gp.obj[23].worldY = 15*gp.size;
        
        gp.obj[24] = new Tree(3);
        gp.obj[24].worldX = 8*gp.size;
        gp.obj[24].worldY = 15*gp.size;

        gp.obj[25] = new Tree(3);
        gp.obj[25].worldX = 13*gp.size;
        gp.obj[25].worldY = 16*gp.size;
        
        gp.obj[26] = new Tree(3);
        gp.obj[26].worldX = 22*gp.size;
        gp.obj[26].worldY = 19*gp.size;

        gp.obj[27] = new Tree(3);
        gp.obj[27].worldX = 10*gp.size;
        gp.obj[27].worldY = 21*gp.size;
        
        gp.obj[28] = new Tree(3);
        gp.obj[28].worldX = 15*gp.size;
        gp.obj[28].worldY = 22*gp.size;
        
        gp.obj[29] = new Tree(1);
        gp.obj[29].worldX = 9*gp.size;
        gp.obj[29].worldY = 23*gp.size;
    }
}
