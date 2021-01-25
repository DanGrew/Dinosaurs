
package uk.dangrew.dinosaurs.game.behaviour.consumption;

import static javafx.scene.paint.Color.*;

import javafx.scene.paint.Color;

public enum ConsumptionWarnings {
   
   NO_WARNING(0, WHITE, "Not hungry...", "Not thirsty..."),
   MILD(50, YELLOW, "Mild hunger.", "Mild thirst."),
   RUMBLY(100, ORANGE, "Rumbly hungry.", "Dry mouth."),
   HANGRY(150, RED, "Hangry.", "Headache."),
   AGGRESSIVE(200, CRIMSON, "Aggressive.", "Migrain");
   
   private final int limit;
   private final Color colour;
   private final String hunger;
   private final String thirst;
   
   private ConsumptionWarnings(int limit, Color colour, String hunger, String thirst) {
      this.limit = limit;
      this.colour = colour;
      this.hunger = hunger;
      this.thirst = thirst;
   }

   public Color colour() {
      return colour;
   }

   public int limit() {
      return limit;
   }

   public String hungerName() {
      return hunger;
   }

   public String thirstName() {
      return thirst;
   }
   
   public static ConsumptionWarnings maximum(){
      return AGGRESSIVE;
   }

   public static ConsumptionWarnings of(int value) {
      if (value >= AGGRESSIVE.limit) {
         return AGGRESSIVE;
      } else if (value >= HANGRY.limit) {
         return HANGRY;
      } else if (value >= RUMBLY.limit) {
         return RUMBLY;
      } else if (value >= MILD.limit) {
         return MILD;
      } else {
         return NO_WARNING;
      }
   }
}
