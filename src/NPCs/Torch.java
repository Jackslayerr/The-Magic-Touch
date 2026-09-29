package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.MapTile;
import Level.NPC;
import Level.Player;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

// This class is for the Torch NPC
public class Torch extends NPC {
    int id;
    MapTile changeToTile;
    boolean puzzleDone = false;
    public Torch(Point location, int id) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("torch-sprites-pixilart (4).png"), 24, 24), "Unlit");
        isInteractable = true;
        talkedToTime = -200;
        this.id = id;
        //textbox.setText("Hello!");
        // textboxOffsetX = -4;
        // textboxOffsetY = -34;
    }

    public void update(Player player) {
        // while npc is being talked to, it raises its tail up (in excitement?)
        if (talkedTo) {
            currentAnimationName = "Lit";
            if (id == 1 && puzzleDone == false) {
                changeToTile = map.getMapTile(14, 7);
                for (int i=7;i<11;i++) {
                    map.setMapTile(15, i, changeToTile);
                    puzzleDone = true;
                }
            }
        } else {
            currentAnimationName = "Unlit";
        }
        

        super.update(player);
    }
    @Override
    public void checkTalkedTo(Player player) {
        // TODO Auto-generated method stub
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
           put("Unlit", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });
            put("Lit", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}

