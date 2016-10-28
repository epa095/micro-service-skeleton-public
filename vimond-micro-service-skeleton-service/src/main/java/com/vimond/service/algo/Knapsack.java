package com.vimond.service.algo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vimond.resources.datastructures.Asset;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Path("pack/")
/**
 * Created by Erik on 24-10-2016.
 * A recursive caching version of the algorithm given in https://en.wikipedia.org/wiki/Knapsack_problem#0.2F1_knapsack_problem
 *
 */
public class Knapsack {
    private static final Logger LOG = LoggerFactory.getLogger(Knapsack.class);


    private final List<Asset> assets;
    ExecutorService a = Executors.newFixedThreadPool(8);
    //"Cache" with no eviction, so it is just a hashmap, but with the handy "load" which tells it how to calculate the value of a key if it is not present.
    LoadingCache<Pair<Integer, Integer>, Container<Asset>> cache = CacheBuilder.newBuilder().build(new CacheLoader<Pair<Integer, Integer>, Container<Asset>>() {
        @Override
        public Container<Asset> load(Pair<Integer, Integer> key) throws Exception {
            return m(key.getLeft(), key.getRight());
        }
    });

    public Knapsack(List<Asset> assets) {
        this.assets = assets;
    }


    private Container<Asset> m(int i, int w) {
        if (i == 0) {
            return new Container<>(null, 0, null);
        } else {
            int thisAssetsDuration = assets.get(i).getDuration();
            Container<Asset> ignoringThisAsset = cache.getUnchecked(new ImmutablePair<>(i - 1, w));
            if (thisAssetsDuration > w) {
                return ignoringThisAsset;
            } else {
                Container<Asset> usingThisAsset = cache.getUnchecked(new ImmutablePair<>(i - 1, w - thisAssetsDuration));
                if (ignoringThisAsset.getValue() > usingThisAsset.getValue() + thisAssetsDuration) {
                    return ignoringThisAsset;
                } else {
                    return new Container<>(usingThisAsset, usingThisAsset.getValue() + thisAssetsDuration, assets.get(i));
                }
            }
        }
    }

    @GET
    @Path("/{timelimit}")
    public List<Asset> getAssets(@PathParam("timelimit") int weightLimit) {
        Container<Asset> m = m(assets.size() - 1, weightLimit);
        List<Asset> ret = m.getPath().stream().map(Container::getElement).filter(asset -> asset != null).collect(Collectors.toList());
        LOG.info("On timelimit: {} we return a list of assets with time: {}", weightLimit,ret.stream().map(Asset::getDuration).reduce(0,(integer, integer2) -> integer+integer2));
        return ret;
    }

    private class Container<T> {
        private final Container<T> previous;
        private final int value;
        private final T element;


        Container(Container<T> previous, int value, T element) {
            this.previous = previous;
            this.value = value;
            this.element = element;
        }

        public Container getPrevious() {
            return previous;
        }

        T getElement() {
            return element;
        }

        int getValue() {
            return value;
        }

        List<Container<T>> getPath() {
            if (previous == null)
                return new LinkedList<>();
            else {
                List<Container<T>> prev = previous.getPath();
                prev.add(this);
                return prev;
            }
        }
    }
}