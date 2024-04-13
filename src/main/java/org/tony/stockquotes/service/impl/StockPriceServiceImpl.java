package org.tony.stockquotes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.tony.stockquotes.entity.StockPrice;
import org.tony.stockquotes.service.StockPriceService;
import org.tony.stockquotes.mapper.StockPriceMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StockPriceServiceImpl extends ServiceImpl<StockPriceMapper, StockPrice>
    implements StockPriceService{
}




