package uk.dangrew.dinosaurs.ui.view;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.dangrew.dinosaurs.game.model.dinosaur.Dinosaur;
import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;
import uk.dangrew.dinosaurs.ui.configuration.GameState;

public class PlayerFocusControllerTest {
   
   private World world;
   private Dinosaur player;
   
   private GameState gameState;
   private WorldViewport worldViewport;
   private PlayerFocusController systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      gameState = new GameState();
      worldViewport = new WorldViewport(gameState);
      systemUnderTest = new PlayerFocusController(gameState, worldViewport);
      
      world = new World("World");
      world.setDimension(10, 10);
      gameState.currentWorld().set(world);
      player = new Dinosaur("Dino");
      player.getWorldLocation().set(new WorldLocation(5, 5));
      gameState.currentPlayer().set(player);
   }
   
   @Test
   public void shouldPanRightWhenMovingOutsideViewport() {
      player.getWorldLocation().set(new WorldLocation(10, 5));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(9, 0)));
   }
   
   @Test
   public void shouldNotPanRightIfWitihinViewPort() {
      player.getWorldLocation().set(new WorldLocation(6, 5));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 0)));
   }

   @Test
   public void shouldPanLeftWhenMovingOutsideViewport() {
      player.getWorldLocation().set(new WorldLocation(10, 5));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(9, 0)));
   }

   @Test
   public void shouldNotPanLeftIfWitihinViewPort() {
      player.getWorldLocation().set(new WorldLocation(4, 5));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 0)));
   }

   @Test
   public void shouldPanTopWhenMovingOutsideViewport() {
      player.getWorldLocation().set(new WorldLocation(5, 0));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 9)));
   }

   @Test
   public void shouldNotPanTopIfWitihinViewPort() {
      player.getWorldLocation().set(new WorldLocation(5, 4));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 0)));
   }

   @Test
   public void shouldPanBottomWhenMovingOutsideViewport() {
      player.getWorldLocation().set(new WorldLocation(5, 9));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 1)));
   }

   @Test
   public void shouldNotPanBottomIfWitihinViewPort() {
      player.getWorldLocation().set(new WorldLocation(5, 6));
      assertThat(worldViewport.topLeft(), equalTo(new WorldLocation(0, 0)));
   }
   
}