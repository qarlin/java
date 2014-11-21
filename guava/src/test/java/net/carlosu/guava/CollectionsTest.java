package net.carlosu.guava;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

public class CollectionsTest {
	private List<Product> prods = new ArrayList<Product>();

	@Before
	public void setUp() throws Exception {
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("desc 1");
		p1.setCategory("cat1");
		prods.add(p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("desc 2");
		p2.setCategory("cat1");
		prods.add(p2);

		Product p3 = new Product();
		p3.setId(3);
		p3.setDescription("desc 3");
		p3.setCategory("cat2");
		prods.add(p3);
	}

	@Test
	public void transformCollectionTest() {
		List<Integer> ids = Lists.transform(prods,
				new Function<Product, Integer>() {
					public Integer apply(Product p) {
						return p.getId();
					}
				});

		Assert.assertEquals(3, ids.size());
		Assert.assertThat(ids, contains(1,2,3));
	}

	@Test
	public void indexCollectionTest() {
		Map<Integer, Product> prodById = Maps.uniqueIndex(prods,
				new Function<Product, Integer>() {
					public Integer apply(Product p) {
						return p.getId();
					}
				});

		Assert.assertEquals(3, prodById.size());
		Assert.assertThat(prodById, hasKey(Integer.valueOf(3)));
	}

	@Test
	public void findItemTest() {
		final int idToFind = 2;
		Product p = Iterables.find(prods, new Predicate<Product>() {
			public boolean apply(Product p) {
				return p.getId() == idToFind;
			}
		});
		Assert.assertEquals(Integer.valueOf(2), p.getId());
	}

	@Test
	public void filteringItemsTest() {
		final String catToFilter = "cat1";
		Iterable<Product> filtered = Iterables.filter(prods,
				new Predicate<Product>() {
					public boolean apply(Product p) {
						return catToFilter.equals(p.getCategory());
					}
				});
		List<Product> list = Lists.newArrayList(filtered);
		Assert.assertEquals(2, list.size());

		Assert.assertFalse(Iterables.isEmpty(filtered));
	}

	@Test
	public void groupItemsTest() {
		ListMultimap<String, Product> prodsByCateg = Multimaps.index(prods,
				new Function<Product, String>() {
					public String apply(Product p) {
						return p.getCategory();
					}
				});
		List<Product> cat1Products = prodsByCateg.get("cat1");
		Assert.assertEquals(2, cat1Products.size());
	}

	@Test
	public void pagingTest() {
		List<Integer> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<List<Integer>> pages = Lists.partition(items, 3);
		for (List<Integer> page : pages) {
			System.out.println(page);// [1,2,3], [4,5,6], [7]
		}
		Assert.assertEquals(3, pages.size());
	}

	@Test
	public void mapBuilderTest() {
		Map<Integer, String> numbers = ImmutableMap.<Integer, String> builder()
				.put(1, "one")
				.put(2, "two")
				.put(3, "three").build();
		Assert.assertEquals(3, numbers.size());
		Assert.assertThat(numbers, hasKey(1));
	}
}
