package com.vendingMachine.vendingMachine;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vendingMachine.vendingMachine.helpers.CoinSize;
import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.BrandStock;
import com.vendingMachine.vendingMachine.model.BrandStockKey;
import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinStock;
import com.vendingMachine.vendingMachine.model.CoinStockKey;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.BrandRepository;
import com.vendingMachine.vendingMachine.repositories.BrandStockRepository;
import com.vendingMachine.vendingMachine.repositories.CoinRepository;
import com.vendingMachine.vendingMachine.repositories.CoinStockRepository;
import com.vendingMachine.vendingMachine.repositories.VendingMachineRepository;


@Component
public class DemoMachineLoader implements CommandLineRunner {

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	VendingMachineRepository vendingMachineRepository;
	
	@Autowired
	CoinRepository coinRepository;
	
	@Autowired
	BrandStockRepository brandStockRepository;
	
	@Autowired
	CoinStockRepository coinStockRepository;

	

	@Override
	public void run(String... strings) throws Exception {
	
	Coin fiveCents = new Coin("5c", BigDecimal.valueOf(0.05), null, CoinSize.SMALL,null);
	Coin tenCents = new Coin("10c", BigDecimal.valueOf(0.10), null, CoinSize.SMALL,null);
	Coin twentyCents = new Coin("20c", BigDecimal.valueOf(0.20), null,CoinSize.MEDIUM,null);
	Coin fiftyCents = new Coin("50c", BigDecimal.valueOf(0.50), null,CoinSize.MEDIUM,null);
	Coin euro= new Coin("1e", BigDecimal.valueOf(1), null, CoinSize.BIG,null);
	Coin twoEuro = new Coin("2e", BigDecimal.valueOf(2), null,CoinSize.BIG,null);

	coinRepository.save(twoEuro);
	coinRepository.save(euro);
	coinRepository.save(fiftyCents);
	coinRepository.save(twentyCents);
	coinRepository.save(tenCents);
	coinRepository.save(fiveCents);

	VendingMachine model01=new VendingMachine("Model01", 6, 10,null, null,220,BigDecimal.valueOf(2),null);
	
	
    Brand cocaCola = new Brand("CocaCola", BigDecimal.valueOf(1.75), null);
    Brand fantaN = new Brand("Fanta Naranja",BigDecimal.valueOf(1.75), null);
    Brand fantaL = new Brand("Fanta Limon", BigDecimal.valueOf(1.75), null);
    Brand cocaColaLight = new Brand("Coca Cola Light", BigDecimal.valueOf(1.75), null);
    Brand aquarius= new Brand("Aquarius", BigDecimal.valueOf(2), null);
    Brand nestea = new Brand("Nestea", BigDecimal.valueOf(2), null);
    
    brandRepository.save(cocaCola); 
    brandRepository.save(cocaColaLight); 
    brandRepository.save(fantaN); 
    brandRepository.save(fantaL); 
    brandRepository.save(aquarius); 
    brandRepository.save(nestea);
    
    vendingMachineRepository.save(model01);

    BrandStockKey keyforCola = new BrandStockKey(cocaCola.getBrandId(),model01.getVendingMachineid());
	BrandStock cocaColaForModel01 = new BrandStock(keyforCola,cocaCola,model01,2);
	
    BrandStockKey keyforColaLight = new BrandStockKey(cocaColaLight.getBrandId(),model01.getVendingMachineid());
	BrandStock cocaColaLightForModel01 = new BrandStock(keyforColaLight,cocaColaLight,model01,1);  
	
    BrandStockKey keyforFantaN = new BrandStockKey(fantaN.getBrandId(),model01.getVendingMachineid());
	BrandStock fantaNForModel01 = new BrandStock(keyforFantaN,fantaN,model01,10);  
	
    BrandStockKey keyforFantaL = new BrandStockKey(fantaL.getBrandId(),model01.getVendingMachineid());
	BrandStock fantaLForModel01 = new BrandStock(keyforFantaL,fantaL,model01,0);  
	
    BrandStockKey keyforAquarius = new BrandStockKey(aquarius.getBrandId(),model01.getVendingMachineid());
	BrandStock aquariusForModel01 = new BrandStock(keyforAquarius,aquarius,model01,10);  
	
    BrandStockKey keyforNestea = new BrandStockKey(nestea.getBrandId(),model01.getVendingMachineid());
	BrandStock nesteaForModel01 = new BrandStock(keyforNestea,nestea,model01,10);  
    
	brandStockRepository.save(cocaColaForModel01);
	brandStockRepository.save(cocaColaLightForModel01);
	brandStockRepository.save(fantaNForModel01);
	brandStockRepository.save(fantaLForModel01);
	brandStockRepository.save(aquariusForModel01);
	brandStockRepository.save(nesteaForModel01);
	
    CoinStockKey keyFor10c=new CoinStockKey(tenCents.getCoinId(),model01.getVendingMachineid());
	CoinStock tenCentsForModel01=new CoinStock(keyFor10c,fiveCents,model01,50);
	
	CoinStockKey keyFor20c=new CoinStockKey(twentyCents.getCoinId(),model01.getVendingMachineid());
	CoinStock twentyCentsForModel01=new CoinStock(keyFor20c,fiveCents,model01,50);
    
	CoinStockKey keyFor50c=new CoinStockKey(fiftyCents.getCoinId(),model01.getVendingMachineid());
	CoinStock fiftyCentsForModel01=new CoinStock(keyFor50c,fiveCents,model01,10);
			
	CoinStockKey keyFor5c=new CoinStockKey(fiveCents.getCoinId(),model01.getVendingMachineid());
	CoinStock fiveCentsForModel01=new CoinStock(keyFor5c,fiveCents,model01,50);
				
	CoinStockKey keyFor1e=new CoinStockKey(euro.getCoinId(),model01.getVendingMachineid());
	CoinStock euroForModel01=new CoinStock(keyFor1e,fiveCents,model01,0);
	
	CoinStockKey keyFor2e=new CoinStockKey(twoEuro.getCoinId(),model01.getVendingMachineid());
	CoinStock twoEuroForModel01=new CoinStock(keyFor2e,fiveCents,model01,0);
	
	coinStockRepository.save(twoEuroForModel01);
	coinStockRepository.save(euroForModel01);
	coinStockRepository.save(fiftyCentsForModel01);
	coinStockRepository.save(twentyCentsForModel01);
	coinStockRepository.save(tenCentsForModel01);
	coinStockRepository.save(fiveCentsForModel01);

  	
    }

	
	

	
	



		


	}


