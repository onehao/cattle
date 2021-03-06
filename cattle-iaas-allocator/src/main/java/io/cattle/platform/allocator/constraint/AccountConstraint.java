package io.cattle.platform.allocator.constraint;

import io.cattle.platform.allocator.service.AllocationAttempt;
import io.cattle.platform.allocator.service.AllocationCandidate;

public class AccountConstraint extends HardConstraint implements Constraint {
    long accountId;

    public AccountConstraint(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean matches(AllocationAttempt attempt, AllocationCandidate candidate) {
        // This constraint is used to build the initial candidate query and to obtain an allocation lock, but it doesn't need to 
        // do any matching since the query will limit allocation based on account id.
        return true;
    }

    public long getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return String.format("account id must be %d", accountId);
    }
}
