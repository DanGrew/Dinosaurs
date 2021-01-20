
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Implementation of an A* algorithm through the {@link World} as a 2d grid.
 */
public class AyeStah {
   
   private final World world;
   
   private final Set<AyeStahNowd> nodesToExploreNext;
   private final List<AyeStahNowd> solutionPath;
   
   public AyeStah(World world) {
      this.world = world;
      this.nodesToExploreNext = new LinkedHashSet<>();
      this.solutionPath = new ArrayList<>();
   }
   
   public List<WorldLocation> search(SearchSession searchSession) throws AyeStahException {
      iterate(new AyeStahNowd(searchSession.origin()), searchSession);
      
      solutionPath.stream()
            .map(AyeStahNowd::location)
            .forEach(System.out::println);
      
      return solutionPath.stream()
            .map(AyeStahNowd::location)
            .collect(Collectors.toUnmodifiableList());
   }
   
   private void iterate(AyeStahNowd current, SearchSession searchSession) throws AyeStahException {
      solutionPath.add(current);
      
      if (current.location().equals(searchSession.destination())) {
         //finished!
         return;
      }
      
      //add search options for up, down, left, right
      processNode(current, searchSession, 0, -1);
      processNode(current, searchSession, 0, 1);
      processNode(current, searchSession, -1, 0);
      processNode(current, searchSession, 1, 0);
      
      Optional<AyeStahNowd> nodeToExploreNext = nodesToExploreNext.stream()
            .sorted(comparing(AyeStahNowd::estimateDistanceFromCurrentToEnd))
            .findFirst();
      if ( !nodeToExploreNext.isPresent()){
         throw new AyeStahException("No nodes left to search. Path cannot be found.");
      }
      iterate(nodeToExploreNext.get(), searchSession);
   }
   
   private void processNode(AyeStahNowd current, SearchSession searchSession, int horizontalChange, int verticalChange) {
      WorldLocation locationToMoveTo = current.location().translate(horizontalChange, verticalChange, world);
      
      //construct node for new search location
      AyeStahNowd node = new AyeStahNowd(locationToMoveTo);
      node.changeParent(current);
      node.calculate(searchSession.destination(), world);
      
      boolean canTraverseToNode = searchSession.worldTraversal().canTraverse(current.location(), locationToMoveTo);
      if (!canTraverseToNode) {
         //not interested in searching if we can't move to it
         return;
      }
      
      Optional<AyeStahNowd> existingNodeResult = nodesToExploreNext.stream()
            .filter(n -> n.location().equals(locationToMoveTo))
            .findFirst();
      if (existingNodeResult.isPresent()) {
         AyeStahNowd existingNode = existingNodeResult.get();
         if (existingNode.distanceBetweenStartAndCurrent() > node.distanceBetweenStartAndCurrent()) {
            //change the path to this node to the shorter version
            existingNode.changeParent(current);
         } else {
            //leave the existing in those to explore
         }
      } else {
         nodesToExploreNext.add(node);
      }
   }
   
}
