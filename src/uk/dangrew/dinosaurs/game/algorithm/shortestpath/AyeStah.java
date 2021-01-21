
package uk.dangrew.dinosaurs.game.algorithm.shortestpath;

import static java.util.Collections.unmodifiableList;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import uk.dangrew.dinosaurs.game.world.World;
import uk.dangrew.dinosaurs.game.world.WorldLocation;

/**
 * Implementation of an A* algorithm through the {@link World} as a 2d grid.
 */
public class AyeStah {
   
   private final World world;
   
   private final Set<AyeStahNowd> nodesToExploreNext;
   private final List<AyeStahNowd> searchedNodes;
   
   public AyeStah(World world) {
      this.world = world;
      this.nodesToExploreNext = new LinkedHashSet<>();
      this.searchedNodes = new ArrayList<>();
   }
   
   public List<WorldLocation> search(SearchSession searchSession) throws AyeStahException {
      nodesToExploreNext.clear();
      searchedNodes.clear();
      
      AyeStahNowd lastNode = iterate(new AyeStahNowd(searchSession.origin()), searchSession);
      return unmodifiableList(extractSolution(lastNode));
   }
   
   private AyeStahNowd iterate(AyeStahNowd current, SearchSession searchSession) throws AyeStahException {
      searchedNodes.add(current);
      
      if (current.location().equals(searchSession.destination())) {
         //finished!
         return current;
      }
      
      //add search options for up, down, left, right
      processNode(current, searchSession, 0, -1);
      processNode(current, searchSession, 0, 1);
      processNode(current, searchSession, -1, 0);
      processNode(current, searchSession, 1, 0);
      
      Optional<AyeStahNowd> nodeToExploreNextResult = nodesToExploreNext.stream()
            .sorted(comparing(AyeStahNowd::estimateDistanceFromCurrentToEnd))
            .findFirst();
      if (!nodeToExploreNextResult.isPresent()) {
         throw new AyeStahException("No nodes left to search. Path cannot be found.");
      }
      AyeStahNowd nextNodeToExplore = nodeToExploreNextResult.get();
      nodesToExploreNext.remove(nextNodeToExplore);
      
      return iterate(nextNodeToExplore, searchSession);
   }
   
   private void processNode(AyeStahNowd current, SearchSession searchSession, int horizontalChange, int verticalChange) {
      WorldLocation locationToMoveTo = current.location().translate(horizontalChange, verticalChange, world);
      
      boolean alreadyVisited = searchedNodes.stream().map(AyeStahNowd::location).anyMatch(l -> l.equals(locationToMoveTo));
      if (alreadyVisited) {
         return;
      }
      
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
   
   private List<WorldLocation> extractSolution(AyeStahNowd lastNode) {
      List<WorldLocation> solutionPath = new ArrayList<>();
      while (lastNode != null) {
         solutionPath.add(lastNode.location());
         lastNode = lastNode.parent();
      }
      Collections.reverse(solutionPath);
      solutionPath.forEach(System.out::println);
      
      return solutionPath;
   }
   
}
