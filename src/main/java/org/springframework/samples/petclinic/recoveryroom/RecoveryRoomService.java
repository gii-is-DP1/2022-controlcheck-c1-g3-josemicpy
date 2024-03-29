package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecoveryRoomService {

	private RecoveryRoomRepository recoveryRoomRepo;

	@Autowired
	public RecoveryRoomService(RecoveryRoomRepository recoveryRoomRepo) {
		this.recoveryRoomRepo = recoveryRoomRepo;
	}

	@Transactional(readOnly = true)
	public List<RecoveryRoom> getAll() {
		return recoveryRoomRepo.findAll();
	}

	@Transactional(readOnly = true)
	public List<RecoveryRoomType> getAllRecoveryRoomTypes() {
		return recoveryRoomRepo.findAllRecoveryRoomTypes();
	}

	@Transactional(readOnly = true)
	public RecoveryRoomType getRecoveryRoomType(String typeName) {
		return recoveryRoomRepo.getRecoveryRoomType(typeName);
	}

	@Transactional(rollbackFor = DuplicatedRoomNameException.class)
	public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
		List<RecoveryRoom> rooms = getAll();
		for (RecoveryRoom r : rooms)
			if (r.getName().equals(p.getName()) && r.getRoomType().equals(p.getRoomType())
					&& !r.getId().equals(p.getId()))
				throw new DuplicatedRoomNameException();
		recoveryRoomRepo.save(p);
		return p;
	}

}
