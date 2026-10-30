package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import Engine.Key;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.MapTile;
import Level.NPC;
import Level.Player;
import Utils.AirGroundState;
import Utils.Point;
import Utils.Puzzle;
import Level.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

// This class is for the Teleporter NPC
public class Teleporter extends NPC {
    int xDestination;
    int yDestination;
    MapTile changeToTile;
    boolean puzzleDone = false;
    int timer;
    boolean wasSpacePressed = false;
    boolean playerNearby = false;
    boolean froze = false;

    public Teleporter(Point location, String spriteFile, int xDestination, int yDestination) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(spriteFile), 32, 32), "DEFAULT");
        isInteractable = true;
        talkedToTime = 201;
        drawTextbox = false;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
        //textbox.setText("Hello!");
        // textboxOffsetX = -4;
        // textboxOffsetY = -34;
    }

    public void update(Player player) {
        if (talkedTo && player.getAirGroundState() == AirGroundState.GROUND) {
            currentAnimationName = "TELEPORTING";
            player.setFrozen(true);
            froze = true;
            
            timer++;
            if (timer >= 150) {
                Point destination = map.getPositionByTileIndex(xDestination, yDestination);
                player.setLocation(destination.x + 16, destination.y);
                player.setFrozen(false);
                timer = 0;
            }
        } else {
            currentAnimationName = "DEFAULT";
            if (froze) {
                player.setFrozen(false);
                froze = false;
            }
        }
        

        super.update(player);
    }

    @Override
    public void checkTalkedTo(Player player) {

        playerNearby = intersects(player);

        boolean spacePressed = Keyboard.isKeyDown(Key.SPACE);

        if (playerNearby && spacePressed && !wasSpacePressed) {
            talkedTo = !talkedTo;
        }

        if (!playerNearby) {
            talkedTo = false;
        }

        wasSpacePressed = spacePressed;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("TELEPORTING", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 30)
                        .withScale(3)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 30)
                        .withScale(3)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 30)
                        .withScale(3)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 30)
                        .withScale(3)
                        .withBounds(1, 1, 32, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 5000)
                        .withScale(3)
                        .withBounds(1, 1, 32, 32)
                        .build()
            });

                put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 30)
                            .withScale(3)
                            .withBounds(1, 1, 32, 32)
                            .build()
                });
            }
        };
    
    }   

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public void setLocation(Player player, float xDestination, float yDestination) {
        player.setLocation(xDestination, yDestination);
    }
}

