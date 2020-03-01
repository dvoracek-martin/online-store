package elasticsearch.product;

import com.dvoracek.exercise.application.product.ProductDto;
import com.dvoracek.exercise.application.product.ProductSearchRequest;
import com.dvoracek.exercise.application.product.ProductSearchService;
import com.dvoracek.exercise.application.shopping.order.ShoppingOrderApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public class ElasticsearchBasedProductSearchService implements ProductSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchBasedProductSearchService.class);

    @Override
    public Page<ProductDto> search(ProductSearchRequest searchRequest, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size, createSort(sort));
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(createQueryBuilder(searchRequest))
//                .withFilter(createFilterBuilder(searchRequest))
//                .withPageable(pageable)
//                .withIndices(this.documentIndexName)
//                .withTypes("_doc")
//                .build();
//
//        if (LOGGER.isTraceEnabled()) {
//            LOGGER.trace("query: {}", searchQuery.getQuery());
//            LOGGER.trace("filter: {}", searchQuery.getFilter());
//        }
//
//        return elasticsearchTemplate.query(searchQuery, response -> extractSearchResult(pageable, response));

        return null;
    }
}
