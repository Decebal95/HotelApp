package models;

import java.util.List;

public class PremiumGuest extends Guest {
    private int promoPercentage;

    public PremiumGuest() {
        super();
    }

    public PremiumGuest(int promoPercentage, String name, String email, String phoneNumber, List<Booking> bookings, float budget) {
        super(name, email, phoneNumber, bookings, budget);

        this.promoPercentage = promoPercentage;
    }

    @Override
    public boolean canAffordRoom(float roomPrice) {
        return (roomPrice - promoPercentage * roomPrice / 100) <= this.getBudget();
    }

    public int getPromoPercentage() {
        return this.promoPercentage;
    }

    public void setPromoPercentage(int promoPercentage) {
        this.promoPercentage = promoPercentage;
    }
}
