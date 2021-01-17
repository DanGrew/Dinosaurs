
package uk.dangrew.dinosaurs.game.mechanism;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssetManagerTest {
   
   private AssetManager systemUnderTest;
   
   @BeforeEach
   public void initialiseSystemUnderTest() {
      systemUnderTest = new AssetManager();
   }
   
   @Test
   public void shouldProvideCollidableAssets() {
      assertThat(systemUnderTest.getCollidableAssetStores(), containsInAnyOrder(systemUnderTest.getTreeStore(), systemUnderTest.getWaterStore()));
   }
   
}
